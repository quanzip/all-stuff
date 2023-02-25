package com.other_for_test.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

public class Strictmapping1 {
    public static void main(String[] args) {
//        khi sd: setSkipNullEnabled(true) thì khi thực hiện map data,
//        các thuộc tính từ nguồn mà có giá trị null thì sẽ không được map sang đối tượng đích.

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);   // (*)
        TypeMap<Animal, Animal> typeMap = modelMapper.createTypeMap(Animal.class, Animal.class);
        typeMap.setProvider( p->  new Animal("des", null, null));

        Animal animal = new Animal(null,
                new SubProperty(5, 6D),
                new SubProperty(100, 101D));
        Animal animal1 = modelMapper.map(animal, Animal.class);
        System.out.println("E -> E: " + animal1);

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Data
    static class Animal{
        String name;
        SubProperty subProperty;
        SubProperty subProperty1;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Data
    static class AnimalDTO{
        String name;

        int leg;
        Double distance;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Data
    static class SubProperty{
        int leg;
        Double distance;
    }
}
