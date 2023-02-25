package com.example.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(fluent = true)
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Setter(AccessLevel.PROTECTED)
    Long id;

    @Column(name = "first_name")
    @Schema(name = "First name of people")
    private String firstName;

    @Column(name = "last_name")
    @Schema(name = "Last name")
    private String lastName;

    @Column(name = "p_number")
    private int pNumber;

}

