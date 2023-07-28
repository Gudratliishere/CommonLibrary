package com.gudratli.commonlibrary.queryfilter.service.gimpl;

import com.gudratli.commonlibrary.queryfilter.dto.SearchFilter;
import com.gudratli.commonlibrary.queryfilter.dto.SpecificationImpl;
import com.gudratli.commonlibrary.queryfilter.service.SearchFilterService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GSearchFilterService implements SearchFilterService
{

    @Override
    public <T> Specification<T> getSpecification (SearchFilter searchFilter)
    {
        Specification<T> specification = null;

        if (searchFilter.getSearchCriteriaList() != null && searchFilter.getSearchCriteriaList().size() > 0)
        {
            specification = Specification.where(
                    new SpecificationImpl<>(searchFilter.getSearchCriteriaList().get(0)));

            for (int i = 1; i < searchFilter.getSearchCriteriaList().size(); i++)
                specification = specification.and(new SpecificationImpl<>(searchFilter.getSearchCriteriaList().get(i)));
        }

        return specification;
    }

    @Override
    public Pageable getPageable (SearchFilter searchFilter)
    {
        Sort sort = null;
        if (searchFilter.getSorting() != null)
        {
            sort = searchFilter.getSorting().getSortDirection().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(searchFilter.getSorting().getSortBy()).ascending() :
                    Sort.by(searchFilter.getSorting().getSortBy()).descending();
        }

        org.springframework.data.domain.Pageable pageable;
        if (searchFilter.getPaging() != null)
        {
            if (sort == null)
                pageable = PageRequest.of(searchFilter.getPaging().getPageNo(), searchFilter.getPaging().getPageSize());
            else
                pageable = PageRequest.of(searchFilter.getPaging().getPageNo(), searchFilter.getPaging().getPageSize(),
                        sort);
        } else
            pageable = PageRequest.of(0, 10);

        return pageable;
    }
}
