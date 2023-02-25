package com.other_for_test.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

public class LooseMapping {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        // flatten fields of property in classes
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        Animal animal = new Animal("Cat", new SubProperty(5, 600D), new SubProperty(6, 6D));
        AnimalDTO animalDTO = modelMapper.map(animal, AnimalDTO.class);
        System.out.println("E -> D: " + animalDTO);

        Animal animalViceVersa = modelMapper.map(animalDTO, Animal.class);
        System.out.println("D -> E: " + animalViceVersa);

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
