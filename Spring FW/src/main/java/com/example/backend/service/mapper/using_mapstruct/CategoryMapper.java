package com.example.backend.service.mapper.using_mapstruct;

import com.example.backend.entity.Category;
import com.example.backend.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category, CategoryDTO>{
    default Category fromId(Integer id){
        return Objects.isNull(id) ? null : Category.builder().id(id).build();
    }
}
