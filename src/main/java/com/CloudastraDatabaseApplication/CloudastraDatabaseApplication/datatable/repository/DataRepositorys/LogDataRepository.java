package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.repository.DataRepositorys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys.LogEntity;

import java.io.IOException;

@Repository
public interface LogDataRepository extends JpaRepository<LogEntity, Long> {

    final ObjectMapper objectMapper = new ObjectMapper();


    public default String convertToDatabaseColumn(Object attribute) {
        if (attribute == null) return null;
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting to JSON", e);
        }
    }


    public default Object convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading JSON", e);
        }
    }
}