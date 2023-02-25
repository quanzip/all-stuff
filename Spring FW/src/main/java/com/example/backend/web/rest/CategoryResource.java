package com.example.backend.web.rest;

import com.example.backend.entity.Category;
import com.example.backend.entity.Symptoms;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.service.dto.CategoryDTO;
import com.example.backend.service.mapper.using_mapstruct.CategoryMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ConditionalOnMissingClass
@RestController(value = "Category")
@RequestMapping(value = "/api")
@Tag(name = "Working with categories of symptoms")
@Log
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping(value = "/category")
    public List<CategoryDTO> getAllCategory(){
        log.info("Gettinh all categories");
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDto(categories);
    }

    @PostMapping(value = "/category")
    public Category saveCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryMapper.toEntity(categoryDTO);
        List<Symptoms> symptoms  = new ArrayList<>();
        symptoms.add(new Symptoms(LocalDateTime.now(), null, "Lung attack", category));
        category.setSymptoms(symptoms);
        Category category1 =categoryRepository.save(category);
        return category1;
    }
}
