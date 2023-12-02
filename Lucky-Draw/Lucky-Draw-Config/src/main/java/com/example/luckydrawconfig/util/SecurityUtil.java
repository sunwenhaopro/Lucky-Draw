package com.example.luckydrawconfig.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;


public class SecurityUtil {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static Object getValue(String name) {
        return threadLocal.get().get(name);
    }

    public static String getString(String name) {
        return Objects.isNull(threadLocal.get().get(name)) ? "" : threadLocal.get().get(name).toString();
    }

    public static String getUsername() {
        return getString("UserName");
    }

    public static Long getUserId() {
        return getLongValue("Id");
    }

    private static Long getLongValue(String id) {
        return Long.parseLong(threadLocal.get().get(id).toString());
    }

    public static void setMap(Map<String, Object> map) {
        threadLocal.set(map);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
