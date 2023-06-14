package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchFilter
{
    private List<SearchCriteria> searchCriteriaList;
    private Paging paging;
    private Sorting sorting;
}
