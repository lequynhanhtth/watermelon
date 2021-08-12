package com.example.watermelon.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public interface ParamService {
    public String getString(String name, String defaultValue);

    public int getInt(String name, int defaultValue);

    public double getDouble(String name, double defaultValue);

    public boolean getBoolean(String name, boolean defaultValue);

    public Date getDate(String name , String pattern);
    public File save(MultipartFile photo, String path);
}
