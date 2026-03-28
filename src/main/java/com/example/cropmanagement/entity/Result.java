package com.example.cropmanagement.entity;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;    // 状态码：200 成功，400/500 失败
    private String msg;      // 提示信息
    private T data;          // 真正的载荷（可以是单棵作物，也可以是列表）

    // 快捷成功的静态方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    // 快捷失败的静态方法
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}