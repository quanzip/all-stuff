package com.example.backend.service.mapper.using_mapstruct;

import com.example.backend.entity.Symptoms;
import com.example.backend.service.dto.SymptomsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SymptomsMapper extends EntityMapper<Symptoms, SymptomsDTO>{

    @Mapping(source = "category.id", target = "categoryId")
    SymptomsDTO toDto(Symptoms symptoms);

    @Mapping(source = "categoryId", target = "category")
    Symptoms toEntity(SymptomsDTO symptomsDTO);

    default Symptoms fromId(Integer id) {
        if (Objects.isNull(id)) return null;
        return Symptoms.builder().id(id).build();
    }
}
