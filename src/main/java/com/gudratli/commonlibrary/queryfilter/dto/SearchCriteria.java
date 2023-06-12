package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.Data;

@Data
public class SearchCriteria
{
    private String key;
    private String operation;
    private Object value;
}
