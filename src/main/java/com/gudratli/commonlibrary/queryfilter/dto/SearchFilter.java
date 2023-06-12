package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchFilter
{
    private List<SearchCriteria> searchCriteriaList;
    private Paging paging;
    private Sorting sorting;
}
