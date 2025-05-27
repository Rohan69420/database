package com.pgl1.database.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class GenericAPIResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int errorCode;
    private long timestamp;
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericAPIResponse<?> that = (GenericAPIResponse<?>) o;
        return success == that.success &&
                errorCode == that.errorCode &&
                timestamp == that.timestamp &&
                Objects.equals(message, that.message) &&
                Objects.equals(data, that.data) &&
                Objects.equals(errors, that.errors) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, data, errors, errorCode, timestamp, path);
    }

    @Override
    public String toString() {
        return "GenericAPIResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                ", errorCode=" + errorCode +
                ", timestamp=" + timestamp +
                ", path='" + path + '\'' +
                '}';
    }
}
