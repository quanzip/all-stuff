package com.example.backend.web.rest;

import com.example.backend.entity.Symptoms;
import com.example.backend.service.SymptomsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/")
@Tag(name = "Symptom resources")
@RequiredArgsConstructor
@Slf4j
public class SymptomsResource {

    private final SymptomsService symptomsService;

    @GetMapping("/symptoms")
    @Operation(description = "Lấy tất cả symptom")
    public ResponseEntity<List<Symptoms>> getAllSymptoms() {
        Integer b = null;
        System.out.println((b + 1));
        List<Symptoms> result = symptomsService.getAllSymptoms();
        log.info("Get all symptoms, size: " + result.size());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/symptoms")
    @Operation(description = "Thêm mới 1 Symptom")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Thêm mới thành công"),
            @ApiResponse(responseCode = "405", description = "Phiên đăng nhập hết hạn, cần đăng nhập lại"),
            @ApiResponse(responseCode = "403", description = "Không có quyền thực hiện chức năng này")
    })
    public ResponseEntity<Symptoms> addSymptom(@Parameter(name = "Triệu chứng",
            description = "Đối tượng triệu chứng cần thêm mới",
            required = true,
            schema = @Schema(implementation = Symptoms.class)) @RequestBody Symptoms symptoms) {
        String fileName = "test.txt";
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                System.out.println("Creating: " + fileName + ": " + file.createNewFile());
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            String result = bufferedReader.readLine();
            if (result.isEmpty())
                System.out.println("Empty input");
            else System.out.println(result);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Add a symptom to database");
        return ResponseEntity.ok(symptomsService.addSymptoms(symptoms));
    }
}
