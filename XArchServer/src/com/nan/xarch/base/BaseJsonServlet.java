package com.nan.xarch.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseJsonServlet<T> extends BaseServlet {

    @Override
    protected void onResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");

        ResponseEntity<T> entity;
        try {
            entity = onHandle(req, resp);
        } catch (Exception e) {
            entity = ResponseEntity.ofFailure();
        }

        PrintWriter writer = resp.getWriter();
        String jsonString = JSON.toJSONString(entity);
        writer.write(jsonString);
        writer.flush();
        writer.close();
    }

    protected abstract ResponseEntity<T> onHandle(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
