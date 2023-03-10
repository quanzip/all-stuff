package com.example.backend;

import com.example.backend.entity.Symptoms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;

import java.time.LocalDateTime;

@TestConfiguration
public class SymptomsTests {

    @Test
    public void testSymptoms() {
        int id = Mockito.anyInt();
        String name = Mockito.anyString();
        LocalDateTime localDateTime = LocalDateTime.now();

        Symptoms symptoms = new Symptoms();
        Symptoms symptoms1 = new Symptoms( localDateTime,id, name, null);
        symptoms.setName(name);
        symptoms.setCreateDate(localDateTime);

        Assertions.assertEquals(id, symptoms.getId());
        Assertions.assertEquals(name, symptoms.getName());
        Assertions.assertEquals(localDateTime, symptoms.getCreateDate());

        Assertions.assertNotEquals(100, symptoms.getId());
        Assertions.assertNotEquals("Quanzip", symptoms.getName());
    }
}
