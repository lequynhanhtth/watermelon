package com.example.watermelon.service;

public interface SessionService {
    public <T> T get(String name);

    public void set(String name, Object value);

    public void remove(String name);
}
