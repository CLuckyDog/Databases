package com.databases.databases.common.utils;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {

    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "ok");
        return obj;
    }

    public static Object ok(Object data) {
        return ok("ok", data);
    }

    public static Object ok(String msg, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", msg);
        obj.put("data", data);
        return obj;
    }

    public static Object pageOk(List data) {
        return pageOk("Ok", data);
    }

    public static Object pageOk(String msg, List data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", msg);
        obj.put("data", result);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 0);
        obj.put("msg", "ERROR");
        return obj;
    }

    public static Object fail(int code, String msg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", code);
        obj.put("msg", msg);
        return obj;
    }
}
