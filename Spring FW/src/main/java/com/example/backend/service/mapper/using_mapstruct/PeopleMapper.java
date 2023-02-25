package com.example.backend.service.mapper.using_mapstruct;

import com.example.backend.entity.People;
import com.example.backend.service.dto.PeopleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PeopleMapper extends EntityMapper<People, PeopleDTO> {
}
