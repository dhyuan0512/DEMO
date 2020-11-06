package com.example.demo.util;

import com.example.demo.enums.DemoEnum;
import com.example.demo.enums.FastEnum;
import com.example.demo.vo.DemoResponse;

public class DemoResponseUtils {

    public DemoResponseUtils() {
    }

    public static DemoResponse<Void> success() {
        return create(DemoEnum.SUCCESS);
    }

    public static DemoResponse<Void> success(String subMessage) {
        return create((FastEnum) DemoEnum.SUCCESS, (String)subMessage);
    }

    public static <E> DemoResponse<E> success(E result) {
        return (DemoResponse<E>) create((FastEnum) DemoEnum.SUCCESS, (Object)result);
    }

    public static <E> DemoResponse<E> success(String subMessage, E result) {
        return (DemoResponse<E>) create((FastEnum) DemoEnum.SUCCESS, subMessage, (Object)result);
    }

    public static <E> DemoResponse<E> exception(String subMessage, E result) {
        return (DemoResponse<E>) create((FastEnum) DemoEnum.EXCEPTION, subMessage, (Object)result);
    }

    public static DemoResponse<Void> exception() {
        return create(DemoEnum.EXCEPTION);
    }

    public static DemoResponse<Void> exception(String subMessage) {
        return create((FastEnum) DemoEnum.EXCEPTION, (String)subMessage);
    }

    public static <E> DemoResponse<E> exception(String message, String subMessage, E result) {
        return create(DemoEnum.EXCEPTION.getCode(), message, subMessage, result);
    }

    public static DemoResponse<Void> create(FastEnum fastEnum) {
        return create(fastEnum.getCode(), fastEnum.getMessage());
    }

    public static DemoResponse<Void> create(FastEnum fastEnum, String subMessage) {
        return create(fastEnum.getCode(), fastEnum.getMessage(), subMessage);
    }

    public static DemoResponse<Void> create(String code, String message) {
        return new DemoResponse(code, message);
    }

    public static DemoResponse<Void> create(String code, String message, String subMessage) {
        return new DemoResponse(code, message, subMessage);
    }

    public static <E> DemoResponse<E> create(String code, String message, String subMessage, E result) {
        return new DemoResponse(code, message, subMessage, result);
    }

    public static <E> DemoResponse<E> create(FastEnum fastEnum, E result) {
        return new DemoResponse(fastEnum.getCode(), fastEnum.getMessage(), result);
    }

    public static <E> DemoResponse<E> create(FastEnum fastEnum, String subMessage, E result) {
        return new DemoResponse(fastEnum.getCode(), fastEnum.getMessage(), subMessage, result);
    }
}
