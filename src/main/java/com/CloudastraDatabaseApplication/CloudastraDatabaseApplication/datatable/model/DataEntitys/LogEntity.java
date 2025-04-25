package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TypeDef;
import jakarta.persistence.*;
// import org.hibernate.type.ObjectType;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "log_table")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "data_id")
    private Long dataId;

    @Column(name = "Objct_type")
    private String ObjectType;



    @Column(name = "object_identifier", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode ObjectIdentifier;


    @Column(name = "updated at")
    private LocalDateTime timeStamp;


    @Column(name = "new_value", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode newValue;


    @Column(name = "old_value", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode oldValue;

    @Column(name = "User_Id")
    private String createdBy;




    public LogEntity() {
    }

    public LogEntity(String operation, Long dataId,String ObjectType , JsonNode newValue, String created_by,LocalDateTime timestamp) {
        this.operation = operation;
        this.dataId = dataId;
        this.ObjectType = ObjectType ;
        this.newValue = (JsonNode) newValue;
        this.createdBy = created_by;
        this.timeStamp = timestamp;
    }

    // --- Getters and Setters ---

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getObjectType() { return ObjectType;}

    public void setObjectType(String objectType) {ObjectType = objectType;    }

    public LocalDateTime getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timeStamp = timestamp;
    }

    public Map<String, Object> getNewValue() {
        return (Map<String, Object>) newValue;
    }

    public void setNewValue(JsonNode newValue) {
        this.newValue = (JsonNode) newValue;
    }

    public Map<String, Object> getOldValue() {
        return (Map<String, Object>) oldValue;
    }


    public void setOldValue(JsonNode oldValue) {
        this.oldValue = (JsonNode) oldValue;
    }

    public Map<String , Object> getObjectIdentifier() {
        return (Map<String, Object>) ObjectIdentifier;
    }

    public void setObjectIdentifier(JsonNode objectIdentifier) {
        this.ObjectIdentifier = (JsonNode) objectIdentifier;
    }

    public String getCreated_by() {
        return createdBy;
    }

    public void setCreated_by(String created_by) {
        this.createdBy = created_by;
    }
}
