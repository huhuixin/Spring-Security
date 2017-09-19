package com.hhx.util;

import java.lang.reflect.Field;

public class ObjectUtil {

    /**
     * 将origin属性注入到destination中
     * @param newData
     * @param oldData
     * @param <T>
     */
    public static <T> void mergeObject(T newData, T oldData) {
        if (newData == null || oldData == null)
            return;
        if (!newData.getClass().equals(oldData.getClass()))
            return;

        Field[] fields = newData.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(newData);
                if (null != value) {
                    fields[i].set(oldData, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }
}
