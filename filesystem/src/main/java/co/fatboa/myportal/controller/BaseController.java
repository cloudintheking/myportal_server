package co.fatboa.myportal.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hl
 * @Description: 控制器基类
 * @Date: 9:07 2018/8/26
 * @Modified By:
 * @Version 1.0
 */
public class BaseController<T> {
    public Map<String, Object> success(Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        Gson gson= new GsonBuilder().serializeNulls().create();

        map.put("status", 1);
        map.put("data", data);
        map.put("message", message);
        return map;
    }

    public Map<String, Object> error(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        map.put("message", message);
        return map;
    }

}
