package com.other_for_test.modelmapper;

import com.example.backend.config.Employee;
import com.example.backend.entity.Symptoms;
import com.example.backend.service.dto.EmployeeDTO;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDateTime;

public class Main_Strict_mapping {
    public static void main(String[] args) {
        Employee employee = new Employee("Quanzip", "Trung thuong", "20 year", null);

        // map basic: using same property's names
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDTO employeeDTO1 = modelMapper.map(employee, EmployeeDTO.class);
        System.out.println("Using model mapper");
        System.out.println("\n-----map basic");
        System.out.println(employeeDTO1);

        // map btw different properties name
        ModelMapper modelMapper1 = new ModelMapper();
        TypeMap<Employee, EmployeeDTO> typeMap = modelMapper1.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMapping(Employee::getPeriod, EmployeeDTO::setCustomPeriod);
        EmployeeDTO employeeDTO2 = modelMapper1.map(employee, EmployeeDTO.class);
        System.out.println("\n-----Map pros with different name");
        System.out.println(employeeDTO2);

        // map name of a property type object in source class to property type String in another class
        ModelMapper modelMapper2 = new ModelMapper();
        TypeMap<Employee, EmployeeDTO> typeMap1 = modelMapper2.createTypeMap(Employee.class, EmployeeDTO.class);
//        typeMap1.addMapping(src -> src.getSymptoms().getName(), EmployeeDTO::setSymptomName);
        typeMap1.addMappings(mapper -> {
            mapper.map(src -> src.getSymptoms().getName(), EmployeeDTO::setSymptomName);
            mapper.map(Employee::getPeriod, EmployeeDTO::setCustomPeriod);
        });
        Employee employee1 = employee;
        employee1.setSymptoms(new Symptoms(LocalDateTime.now(), 200, "Employee Headache", null));
        EmployeeDTO employeeDTO3 = modelMapper2.map(employee1, EmployeeDTO.class);
        System.out.println("\n-----map object to property");
        System.out.println(employeeDTO3);

        // skip properties that no need to expose to DTO
        ModelMapper modelMapper3 = new ModelMapper();
        TypeMap<Employee, EmployeeDTO> typeMap2 = modelMapper3.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap2.addMappings(mapper -> {
            mapper.skip(EmployeeDTO::setSymptomName); // no transfer symptom's name to DTO
            mapper.skip(Employee::getSymptoms, EmployeeDTO::setCustomPeriod); // no CustomPeriod seted
        });
        EmployeeDTO employeeDTO4= modelMapper3.map(employee1, EmployeeDTO.class);
        System.out.println("\n-----skip some pros");
        System.out.println(employeeDTO4);

        // Mapping with conditionals
        Condition<String, String> employeeEmployeeDTOCondition = mappingContext -> mappingContext.getSource().equals(mappingContext.getDestination());
        ModelMapper modelMapper4 = new ModelMapper();
        TypeMap<Employee, EmployeeDTO> typeMap3 = modelMapper4.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap3.addMappings(mapper -> {
            mapper.when(employeeEmployeeDTOCondition).skip(src -> src.getSymptoms().getName(), EmployeeDTO::setSymptomName);
            mapper.when(employeeEmployeeDTOCondition).map(Employee::getAdd, EmployeeDTO::setAdd);
//            ... more map
        });
        EmployeeDTO employeeDTO5= modelMapper4.map(employee1, EmployeeDTO.class);
        System.out.println("\n-----map using condition");
        System.out.println(employeeDTO5);

        // using converter
        ModelMapper modelMapper5 = new ModelMapper();
        Converter<Symptoms, String> converter = symptom -> symptom.getSource().getId().toString();
        TypeMap<Employee, EmployeeDTO> typeMap4 = modelMapper5.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap4.addMappings(mapper -> {
            mapper.using(converter).map(Employee::getSymptoms, EmployeeDTO::setCustomPeriod);
        });
        EmployeeDTO dto = modelMapper5.map(employee1, EmployeeDTO.class);
        System.out.println("\n-----map using converter");
        System.out.println(dto);

        // convert versus condition
        ModelMapper modelMapper6 = new ModelMapper();
        final Converter<Symptoms, String> converter1 = symptom -> symptom.getSource().getName();
        final Condition<Symptoms, String> condition = ctx -> {
            return  ctx.getSource() != null && ctx.getDestination() == null;
        };
        TypeMap<Employee, EmployeeDTO> typeMap5 = modelMapper6.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap5.addMappings(mapper -> {
            mapper.when(condition).using(converter1).map(Employee::getSymptoms, EmployeeDTO::setSymptomName);
        });
        EmployeeDTO dto1 = modelMapper6.map(employee1, EmployeeDTO.class);
        System.out.println("\n-----map using converter & condition");
        System.out.println(dto1);
    }
}
