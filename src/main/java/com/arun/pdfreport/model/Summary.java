package com.arun.pdfreport.model;

import lombok.Data;

@Data
public class Summary {
    private ServiceModel serviceModel;
    private String mandateType;
    private String periodicReviewDate;
}
