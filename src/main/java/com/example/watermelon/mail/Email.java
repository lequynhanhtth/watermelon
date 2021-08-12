package com.example.watermelon.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    String to;
    String from;
    String subject;
    String content;
    private Map< String, Object > model;

}
