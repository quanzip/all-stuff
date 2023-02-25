package com.other_for_test.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperForList {
    public static void main(String[] args) {

        ModelMapper modelMapper = getMapper();
        List<Integer> integers = new ArrayList() {
        };
        integers.addAll(Arrays.asList(1, 2, 0, 4, 5));

        List<String> strings = modelMapper.map(integers, new TypeToken<List<String>>() {}.getType());
        System.out.println(strings);
    }

    public static ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
