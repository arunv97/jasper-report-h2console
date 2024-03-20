package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class ServiceModel {
    private String desc;
    public ServiceModel(String desc) {
        this.desc = desc;
    }
}

