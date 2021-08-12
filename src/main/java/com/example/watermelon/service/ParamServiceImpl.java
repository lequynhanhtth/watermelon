package com.example.watermelon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    HttpServletRequest req;
    @Autowired
    ServletContext app;

    @Override
    public String getString(String name, String defaultValue) {
        String a = req.getParameter(name);
        if (a.equals("") || a == null) {
            return defaultValue;
        } else {
            return a;
        }

    }

    @Override
    public int getInt(String name, int defaultValue) {
        try {
            int a = Integer.parseInt(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(String name, double defaultValue) {
        try {
            double a = Double.parseDouble(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        try {
            Boolean a = Boolean.parseBoolean(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(String name, String pattern) {
        if (name != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(name);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return null;
    }

    @Override
    public File save(MultipartFile photo, String path) {
        File dir = new File("");
        dir = new File(dir.getAbsolutePath() + path);

        if (!dir.exists())
            dir.mkdirs();
        try {
            String filename = photo.getOriginalFilename();
            File savefile = new File(dir + "/" + filename);
            photo.transferTo(savefile);
            return savefile;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
