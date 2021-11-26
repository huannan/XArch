#!/usr/bin/env bash

./gradlew :app:dependencies | grep '^[+|\\\n]' > .dep.txt
