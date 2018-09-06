package co.fatboa.core.restcontroller;

import org.springframework.data.domain.Page;

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
    /**
     * 操作成功返回结果
     *
     * @param data
     * @param message
     * @return
     */
    public Map<String, Object> successResult(Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("data", data);
        map.put("message", message);
        return map;
    }

    /**
     * 操作失败返回结果
     *
     * @param message
     * @return
     */
    public Map<String, Object> errorResult(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        map.put("message", message);
        return map;
    }

    /**
     * 分页结果封装
     *
     * @param page
     * @return
     */
    public Map<String, Object> pageResult(Page<T> page) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("content", page.getContent());
        data.put("total", page.getTotalElements());
        result.put("data", data);
        result.put("status", 1);
        result.put("message", "操作成功");
        return result;
    }

}
