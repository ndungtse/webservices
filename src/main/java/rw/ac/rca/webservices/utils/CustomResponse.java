package rw.ac.rca.webservices.utils;

public class CustomResponse<T> {
private String message;
    private T data;

    public CustomResponse() {
        this.message = "Operation successful";
        this.data = null;
    }

    public CustomResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public CustomResponse(String message) {
        this.message = message;
    }

    public CustomResponse(T data) {
        this.message = "Operation successful";
        this.data = data;
    }

    public CustomResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
