package com.example.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Author: qinbocheng
 * Date: 2022/5/7 17:08
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/7 17:08 版本号    描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String code;
    private String msg;
    private T      data;

    /**
     * 接口调用成功时出参
     *
     * @param data
     *            接口返回数据
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> BaseResponse<T> returnOK(T data) {
//        return restResult("200", "接口调用成功!", data);
        return new BaseResponse<>("200", "接口调用成功!", data);
    }

    /**
     * 调用失败
     *
     * @param code
     *            错误码
     * @param message
     *            错误信息
     * @return
     */
    public static <T> BaseResponse<T> returnError(String code, String message) {
//        return restResult(code, message, null);
        return new BaseResponse<>(code, message, null);
    }

    /**
     * 调用失败
     *
     * @param message
     *            错误信息
     * @return
     */
    public static <T>  BaseResponse<T> returnError(String message) {
//        return restResult("-1", message, null);
        return new BaseResponse<>("-1", message, null);
    }

//    public static <T> BaseResponse<T> restResult(String code, String msg, T data) {
//       BaseResponse<T> baseResponse = new BaseResponse<>();
//       baseResponse.setCode(code);
//       baseResponse.setData(data);
//       baseResponse.setMsg(msg);
//       return baseResponse;
//    }


//    @Override
//    public String toString() {
//        return super.toString();
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
}
