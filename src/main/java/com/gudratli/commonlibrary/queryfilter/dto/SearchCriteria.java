package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria
{
    private String key;
    private String operation;
    private Object value;
}
