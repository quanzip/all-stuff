package com.other_for_test.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperForList {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList(){
            @Override
            public boolean add(Object ob1) {
                if(Integer.parseInt(ob1.toString()) < 0){
                    System.out.println("cannot add item that smaller than 0");
                    return false;
                }else {
                    return super.add(ob1);
                }
            }

            @Override
            public int indexOf(Object o) {
                if(Integer.parseInt(o.toString()) < 0){
                    System.out.println("cannot find item at index < 0");
                    return -1;
                }else {
                    return super.indexOf(o);
                }
            }
        };

        integers.addAll(Arrays.asList(1,2,0,4,5).subList(0,2));

        integers.add(-1); // print err because index = -1
        integers.add(new Integer(3));
        System.out.println(integers.indexOf(-8)); // print err because index = -8
        System.out.println(integers);

//        integers.add(1);
//        integers.add(2);
//        integers.add(3);
//        List<Integer> strings = modelMapper.map(integers, new TypeToken<List<String>>() {}.getType());
//        System.out.println(strings);
    }
}
