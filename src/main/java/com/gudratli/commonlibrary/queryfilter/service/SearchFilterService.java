package com.gudratli.commonlibrary.queryfilter.service;

import com.gudratli.commonlibrary.queryfilter.dto.Paging;
import com.gudratli.commonlibrary.queryfilter.dto.SearchCriteria;
import com.gudratli.commonlibrary.queryfilter.dto.SearchFilter;
import com.gudratli.commonlibrary.queryfilter.dto.Sorting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SearchFilterService
{
    /**
     * Extracts the list of SearchCriteria from the provided SearchFilter and creates a generic Specification<T> class.
     *
     * @param searchFilter The SearchFilter containing the SearchCriteria.
     * @param <T> The type parameter for the Specification.
     * @return A Specification<T> instance based on the extracted SearchCriteria, or null if the SearchCriteria list is null or empty.
     * @see SearchFilter
     * @see SearchCriteria
     * @see Specification
     */
    <T> Specification<T> getSpecification (SearchFilter searchFilter);

    /**
     * Constructs a Pageable instance from the provided SearchFilter, which contains Paging and Sorting classes.
     *
     * @param searchFilter The SearchFilter object containing the Paging and Sorting information.
     * @return A Pageable instance based on the provided Paging and Sorting information. If Paging is null, the first page will contain 10 items.
     * @see SearchFilter
     * @see Paging
     * @see Sorting
     * @see Pageable
     */
    Pageable getPageable (SearchFilter searchFilter);
}
