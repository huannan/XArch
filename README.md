# XArch - 手把手带你搭建Android架构

### 前言

最近公司准备上线新项目，由我来负责搭建架构，正好也把之前学的Kotlin等相关知识巩固一下，于是把搭建的成果抽取出来作为开源项目分享给大家。

### 架构总体介绍

一个良好的架构需要什么，根据设计原则，有以下：

* 实现项目所需要的功能，为业务需求打下基础
* 可扩展性、可配置性足够强大
* 易用性，方便新成员学习和上手
* 代码高可复用性，添加新功能的时候可以重用大部分已有代码

在开始之前，看了公司内部很多项目的架构，大部分都不如人意，诸如以下的问题满天飞：

* 原有API十分难用，比如说添加一个简单的埋点，大部分情况需要去查看和拷贝已有的代码才能使用
* Adapter满天飞，列表Item没有复用，本来应该复用的Item写了多次，Adapter也是一个页面写一个，非常难以统一管理
* 网络架构十分乱，同一个项目由于历史原因会有多个网络架构，并且十分难以使用，每次使用还要手动去创建一次Retrofit的Service才能调用API
* findViewById满天飞，想要重构代码的时候需要修改大量内容
* 包划分混乱等等

下面是学习该架构可以学习、巩固的知识点：

* Kotlin各种语法等
* Jetpack：主要是ViewModel、LifeCycle、LiveData、Room、ViewBinding
* Kotlin协程
* 多线程带来的线程同步的处理
* Retrofit+OkHttp
* MultiType
* MMKV
* 等等

下面来看一下项目总体的包划分：

![项目总体架构](https://upload-images.jianshu.io/upload_images/2570030-de5aaf553241144f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

base：存放所有业务的基础类，包括BaseActivity、BaseFragment、BaseViewModel、列表等功能的封装
bean：存放所有Bean类，一般多为Kotlin的data class
constant：存放所有常量
eventbus：项目封装XEventBus，基于LiveData
item：存放所有可重用的列表Item
module：存放以业务功能划分（一般是以页面为划分界限）的所有模块，每一个模块的package包含模块所需要的类，一般为Activity/Fragment以及与之对应的ViewModel
network：基于Retrofit+OkHttp+协程的网络架构封装
persistence：存放数据库以及键值对等持久化相关的类
util：工具类，包含Kotlin扩展属性、扩展函数
widget：存放所有自定义控件
XArchApplication：项目的Application

### 基类封装

下面先从最简单的基类的封装入手，直接上代码：

```kotlin
abstract class BaseActivity : SwipeBackActivity(), IGetPageName {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSwipeBackEnable(swipeBackEnable())
    }

    override fun onStart() {
        super.onStart()
        // 这里可以添加页面打点
    }

    override fun onStop() {
        super.onStop()
        // 这里可以添加页面打点
    }

    /**
     * 默认开启左滑返回,如果需要禁用,请重写此方法
     */
    protected open fun swipeBackEnable() = true

    ...
}
```

1. 从整体来看，基类的设计必须是一个abstract class，并且提供必要的钩子函数给子类定制以及提供公共的常用的函数
2. 在基类的生命周期函数里面可以做一些统一的操作，一般来说是页面的打点，其中pageName可以通过基础基类实现IGetPageName来提供
3. 对于Activity而言，有一些页面可能需要右滑返回功能，我们直接让BaseActivity继承SwipeBackActivity即可

其他的BaseFragment、BaseViewModel都比较简单，就不再赘述了

### 底部导航栏的实现



### 列表架构封装

这一块是整个项目的重中之重，也是项目里面最为常用和复杂的功能，如果封装得不好，会很影响开发效率和项目质量，这一块的痛点需求有：

* Adapter和Item的高可复用
* 与ViewModel打通，通过ViewModel加载数据
* 可配置性足够强大，但是又无需重复配置一些累赘的属性，比如Adapter、LayoutManager等
* 支持下拉刷新、上拉加载的开启和关闭
* 支持数据预加载
* 空白页、异常页面
* 支持本地数据加载、网络数据加载。在加载网络数据异常的情况下根据网络状态自动重试
* 支持常见的监听，比如短按、长按、Item子View的点击监听
* 等等

基于以上思考，笔者为项目封装了一个XRecyclerView控件，使用的方法很简单：

1. 在xml里面放置一个XRecyclerView
2. 在代码里面进行简单的配置
3. 继承BaseRecyclerViewModel并且实现里面的最核心的loadData方法

XRecyclerView的配置示例代码在HomeFragment.kt，如下：

```kotlin
viewBinding.rvList.init(
    XRecyclerView.Config()
        .setViewModel(viewModel)
        .setOnItemClickListener(object : XRecyclerView.OnItemClickListener {
            override fun onItemClick(parent: RecyclerView, view: View, viewData: BaseViewData<*>, position: Int, id: Long) {
                Toast.makeText(context, "条目点击: ${viewData.value}", Toast.LENGTH_SHORT).show()
            }
        })
        .setOnItemChildViewClickListener(object : XRecyclerView.OnItemChildViewClickListener {
            override fun onItemChildViewClick(parent: RecyclerView, view: View, viewData: BaseViewData<*>, position: Int, id: Long, extra: Any?) {
                if (extra is String) {
                    Toast.makeText(context, "条目子View点击: $extra", Toast.LENGTH_SHORT).show()
                }
            }
        })
)
```

通过调用XRecyclerView的init方法，传入一个包含着所有配置信息的XRecyclerView.Config对象即可。其中大部分配置如果无需配置的话可以不进行配置直接使用推荐的默认值。

在示例代码当中，我们设置了Item的点击监听和Item上面子View的点击监听，另外我们还传入了一个BaseRecyclerViewModel对象，主要负责为XRecyclerView提供数据来源，实现可参考HomeViewModel.kt，如下：

```kotlin
class HomeViewModel : BaseRecyclerViewModel() {

    override fun loadData(isLoadMore: Boolean, isReLoad: Boolean, page: Int, offset: Int) {
        viewModelScope.launch {
            // 模拟网络数据加载
            delay(1000L)

            val time = DateFormat.format("MM-dd HH:mm:ss", System.currentTimeMillis())

            val viewDataList: List<BaseViewData<*>>
            if (!isLoadMore) {
                viewDataList = listOf<BaseViewData<*>>(
                    Test1ViewData("a-$time"),
                    Test2ViewData("b-$time"),
                    Test1ViewData("c-$time"),
                    Test2ViewData("d-$time"),
                    Test1ViewData("e-$time"),
                    Test2ViewData("f-$time"),
                    Test1ViewData("g-$time"),
                    Test2ViewData("h-$time"),
                )
            } else {
                // 在第5页模拟网络异常
                if (page == 5) {
                    postError(isLoadMore)
                    return@launch
                }
                viewDataList = listOf<BaseViewData<*>>(
                    Test1ViewData("a-$time"),
                    Test2ViewData("b-$time"),
                    Test1ViewData("c-$time"),
                    Test2ViewData("d-$time"),
                    Test1ViewData("e-$time"),
                    Test2ViewData("f-$time"),
                    Test1ViewData("g-$time"),
                    Test2ViewData("h-$time"),
                )
            }
            postData(isLoadMore, viewDataList)
            // postError(isLoadMore)
        }
    }

    @PageName
    override fun getPageName() = PageName.HOME
}
```

在示例代码中，我们主要做了以下几件事：

1. 继承BaseRecyclerViewModel，实现最重要的loadData函数
2. 在loadData，我们可以获取到当前加载是否为首页数据加载或者是更多数据加载，是否为重试加载，页码以及游标偏移等
3. 根据上述参数，开启协程，请求数据，把数据封装成BaseViewData<*>列表，通过调用父类提供的postData提交数据
4. 也可以通过调用父类提供的postError提交加载出错

下面开始简要说明列表架构的实现原理。整体架构如下图所示：

![列表架构](https://upload-images.jianshu.io/upload_images/2570030-7e7b22c8d4e8fb36.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



要考虑Item和Adapter的复用，我们通过源码的方式引入MultiType，封装了一个BaseAdapter，并且在里面提供一些最通用的函数：

```kotlin
open class BaseAdapter : MultiTypeAdapter() {

    init {
        register(LoadMoreViewDelegate())
        register(Test1ViewDelegate())
        register(Test2ViewDelegate())
        register(Test3ViewDelegate())
    }

    open fun setViewData(viewData: List<BaseViewData<*>>) {
        items.clear()
        items.addAll(viewData)
        notifyDataSetChanged()
    }

    ...
}
```

MultiType的核心思想是一种class对应一种Item，为了进一步隔离并且使得相同的class可以对应多种Item，我们抽象了一个BaseViewData包装类：

```kotlin
open class BaseViewData<T>(var value: T) {
    ...
}
```

那么通过继承实现不同的BaseViewData就可以不同的Item，同时，我们也需要把MultiType的源码作相应修改。

