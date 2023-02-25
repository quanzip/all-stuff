package com.example.backend.web.rest;

import com.example.backend.entity.Pet;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Pet APIS", description = "Pet API entry to get Data of pet")
@Transactional
public class PetResource {

    @PostMapping(value = "/pet")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet){
        return ResponseEntity.ok(pet);
    }
}
