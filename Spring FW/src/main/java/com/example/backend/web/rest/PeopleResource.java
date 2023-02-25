package com.example.backend.web.rest;

import com.example.backend.repository.PeopleRepository;
import com.example.backend.service.dto.PeopleDTO;
import com.example.backend.service.mapper.using_mapstruct.PeopleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "People resources-quanzip")
@RequiredArgsConstructor
@Slf4j // @XSlf4j @CommonsLog @Log4 @Log   Alternatives for using log
public class PeopleResource {

    private final PeopleRepository peopleRepository;
    private final PeopleMapper peopleMapper;

    @GetMapping(value = "/people")
    @Operation(description = "Xem danh sách mọi người trong hệ thống")
    @Schema(description = "D/s mọi người trong hệ thống")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Page Not found"),
            @ApiResponse(responseCode = "403", description = "No permission!"),
    })
    public List<PeopleDTO> getALlPeople() {
        log.info("Getting all people");
        return peopleMapper.toDto(peopleRepository.findAll());
    }
}
