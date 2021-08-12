package com.example.watermelon.service;

import javax.servlet.http.Cookie;

public interface CookieService {
    public Cookie getCookie(String name);

    public String getValue(String name);

    public void addCookie(String name, String value, int hours);

    public void removeCookie(String name);
}
