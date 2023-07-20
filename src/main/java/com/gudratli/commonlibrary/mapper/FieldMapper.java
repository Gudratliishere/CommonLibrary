package com.gudratli.commonlibrary.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author : Dunay Gudratli
 * @mailto : d.qudretli@gmail.com
 * @since : 20.07.2023
 **/
@Component
public class FieldMapper
{
    public <T> void map (Map<Object, Object> source, T target)
    {
        source.forEach((key, value) ->
        {
            Field field = ReflectionUtils.findField(target.getClass(), (String) key);
            assert field != null;
            field.setAccessible(true);
            if (field.getType() == Double.class)
                ReflectionUtils.setField(field, target, Double.valueOf(value.toString()));
            else
                ReflectionUtils.setField(field, target, value);
        });
    }
}
