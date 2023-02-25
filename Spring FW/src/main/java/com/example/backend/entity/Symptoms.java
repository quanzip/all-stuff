package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "symptoms")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Builder
public class Symptoms {

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(name = "Ngày tạo symptom")
    LocalDateTime createDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "UUID của symptom")
    private @Setter(AccessLevel.PRIVATE)
    Integer id;
    @Column(name = "name", length = 255)
    @Schema(name = "Tên của triệu chứng")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    @Schema(name = "Reference of category")
    private Category category;
}
