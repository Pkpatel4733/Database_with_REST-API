package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
@Table(name="data_table")
public class Clouduser {
   
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
    
    @Column(name="status")
    private String status;

     @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version 
    private Long version;
    
     @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        version = 1L; 
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Clouduser() {
        
    }

    public Clouduser(String name, String description, String status ) {
        // this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public void setName(String name) {
        this.name = name;
    }
   

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }


    public  String getDescription() {
        return description;
    }


    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Clouduser(Clouduser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.description = user.getDescription();
        this.status = user.getStatus();
        this.updatedAt = user.getUpdatedAt();
        
    }
    

}
