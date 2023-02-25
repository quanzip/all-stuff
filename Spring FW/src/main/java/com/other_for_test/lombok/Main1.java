package com.other_for_test.lombok;

import com.example.backend.entity.CustomObject;

public class Main1 {
    public static void main(String[] args) {
        CustomObject customObject = CustomObject.builder()
                .id(22)  // when id is not final
                .name("Quanzip") // when id is not final
//                .unUsed(true)  // Error because this prop is final
                .build();
//        customObject.id(22);  Error because: private @Setter(AccessLevel.PROTECTED) int id;

        System.out.println(customObject);
    }
}
