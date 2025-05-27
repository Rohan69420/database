package com.pgl1.database.util;

import com.pgl1.database.handler.GenericAPIResponse;

import java.util.Arrays;
import java.util.List;

public class ResponseUtil {
    public static <T> GenericAPIResponse<T> success(T data, String message, String path) {
        GenericAPIResponse<T> response = GenericAPIResponse.<T>builder().success(true).message(message).data(data).errors(null).errorCode(0).timestamp(System.currentTimeMillis()).path(path).build();
        return response;
    }

    public static <T> GenericAPIResponse<T> error(List<String> errors, String message, int errorCode, String path) {
        GenericAPIResponse<T> response = GenericAPIResponse.<T>builder().success(false).message(message).data(null).errors(errors).errorCode(errorCode).timestamp(System.currentTimeMillis()).path(path).build();
        return response;
    }

    public static <T> GenericAPIResponse<T> error(String error, String message, int errorCode, String path) {
        return error(Arrays.asList(error), message, errorCode, path);
    }
}
