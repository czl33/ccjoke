package com.newczl.lib_network;

public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
