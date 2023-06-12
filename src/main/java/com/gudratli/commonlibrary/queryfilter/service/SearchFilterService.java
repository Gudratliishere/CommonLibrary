package com.gudratli.commonlibrary.queryfilter.service;

import com.gudratli.commonlibrary.queryfilter.dto.SearchFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SearchFilterService
{
    <T> Specification<T> getSpecification (SearchFilter searchFilter);

    Pageable getPageable (SearchFilter searchFilter);
}
