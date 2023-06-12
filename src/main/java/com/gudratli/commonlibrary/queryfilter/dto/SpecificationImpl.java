package com.gudratli.commonlibrary.queryfilter.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class SpecificationImpl<T> implements Specification<T>
{
    private final SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate (@NonNull Root<T> root, @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder)
    {
        if (searchCriteria.getOperation().equalsIgnoreCase(">"))
            return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        else if (searchCriteria.getOperation().equalsIgnoreCase("<"))
            return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        else if (searchCriteria.getOperation().equalsIgnoreCase(":"))
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class)
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            else
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());

        return null;
    }
}
