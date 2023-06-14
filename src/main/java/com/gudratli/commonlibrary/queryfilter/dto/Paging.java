package com.gudratli.commonlibrary.queryfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paging
{
    private int pageNo;
    private int pageSize;

    public static Paging of (final Integer pageNo, final Integer pageSize)
    {
        if (pageNo != null && pageSize != null)
            return new Paging(pageNo, pageSize);
        return null;
    }
}
