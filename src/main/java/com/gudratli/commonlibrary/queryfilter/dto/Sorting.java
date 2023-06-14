package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sorting
{
    private String sortBy;
    private String sortDirection;
}
