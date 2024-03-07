package com.arun.pdfreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    private String feeType;
    private String listPrice;
    private String effectivePrice;

}

