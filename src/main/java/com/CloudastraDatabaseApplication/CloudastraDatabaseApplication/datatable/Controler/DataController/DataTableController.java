package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.Controler.DataController;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys.Clouduser;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.services.DataServices.ClouduserService;

@RestController
@RequestMapping("/data")
public class DataTableController {

    @Autowired
    private ClouduserService clouduserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDataById(@PathVariable Long id) {
        Optional<Clouduser> data = clouduserService.getUserById(id);
        
        
        if (data.isPresent()) {
            return ResponseEntity.ok(data.get()); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Data with ID " + id + " not found");  
        }
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<String> addUsers(@RequestBody Clouduser users) {
        clouduserService.saveData(users);

        return ResponseEntity.ok("Users saved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clouduser> updateClouduser(@PathVariable Long id, @RequestBody Clouduser updatedData) {
        Clouduser updatedUser = clouduserService.updateData(id, updatedData); 
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Long id) {
    Optional<Clouduser> existingData = clouduserService.getUserById(id);
    
    if (existingData.isPresent()) {
        clouduserService.deleteUserbyId(id);  
        return ResponseEntity.ok("Record with ID " + id + " deleted successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record with ID " + id + " not found");
    }
}
}



