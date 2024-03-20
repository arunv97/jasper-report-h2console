package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class Owner {
    private String rmFullName;
    public Owner(String rmFullName) {
        this.rmFullName = rmFullName;
    }

}

