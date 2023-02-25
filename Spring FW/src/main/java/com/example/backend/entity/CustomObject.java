package com.example.backend.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @ToString(exclude = {"unUsed"}) // do not print property: unUsed in toString method
@EqualsAndHashCode
@Accessors(fluent = true)
@Builder
public class CustomObject {
    @Value(value = "false")
    private final @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Boolean unUsed = false;
    private @Setter(AccessLevel.PROTECTED)
    int id;
    @Value(value = "${name: Quanzip by default}")
    private @Setter(AccessLevel.PUBLIC)
    String name = "";
}
