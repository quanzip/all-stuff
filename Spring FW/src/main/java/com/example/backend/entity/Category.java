package com.example.backend.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Symptoms> symptoms;

    public Category(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public Category() {
    }

    public Category(Integer id, Integer type, List<Symptoms> symptoms) {
        this.id = id;
        this.type = type;
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
