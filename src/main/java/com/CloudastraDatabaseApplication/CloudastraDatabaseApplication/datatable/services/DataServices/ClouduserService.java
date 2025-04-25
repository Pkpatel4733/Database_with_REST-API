package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.services.DataServices;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys.Clouduser;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys.LogEntity;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.repository.DataRepositorys.ClouduserRepository;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.repository.DataRepositorys.LogDataRepository;
import jakarta.transaction.Transactional;
import static com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.repository.DataRepositorys.LogDataRepository.objectMapper;

@Service
public class ClouduserService {

    @Autowired
    private ClouduserRepository clouduserRepository;
    @Autowired
    private LogDataRepository dataRepository;


    public Clouduser saveData(Clouduser users) {
        Clouduser savedData = clouduserRepository.save(users);

        Map<String, Object> logDataMap = new HashMap<>();
        logDataMap.put("Name", savedData.getName());
        logDataMap.put("Description", savedData.getDescription());
        logDataMap.put("Status", savedData.getStatus());

        JsonNode logDataJson = objectMapper.valueToTree(logDataMap);


        LogEntity log = new LogEntity("INSERT", savedData.getId(),"/data" ,logDataJson,savedData.getName(),LocalDateTime.now());

        dataRepository.save(log);

        return savedData;
    }


    public void logUpdate(Clouduser user,Clouduser oldUser, String command) {

        Map<String, Object> newUserDetails = new HashMap<>();
        newUserDetails.put("Name", user.getName());
        newUserDetails.put("Description", user.getDescription());
        newUserDetails.put("Status", user.getStatus());
        JsonNode newValue = objectMapper.valueToTree(newUserDetails);


        Map<String, Object> oldUserDetails = new HashMap<>();
        oldUserDetails.put("Name", oldUser.getName());
        oldUserDetails.put("Description", oldUser.getDescription());
        oldUserDetails.put("Status", oldUser.getStatus());
        JsonNode oldValue = objectMapper.valueToTree(oldUserDetails);

        Map<String, Object> ObjectIdentifire = new HashMap<>();
        ObjectIdentifire.put("name", user.getName());
        ObjectIdentifire.put("data_Id",user.getId());
        JsonNode Objectidentifire = objectMapper.valueToTree(ObjectIdentifire);

        LogEntity log = new LogEntity();
        log.setNewValue(newValue);
        log.setOldValue(oldValue);
        log.setOperation(command);
        log.setObjectIdentifier(Objectidentifire);
        log.setObjectType("/data");
        log.setCreated_by(user.getName());
        log.setDataId(user.getId());
        log.setTimestamp(LocalDateTime.now());
        dataRepository.save(log);

    }
    

    @Transactional
    public Clouduser updateData(Long id, Clouduser updatedData) {
        Optional<Clouduser> existingData = clouduserRepository.findById(id);
        
        if (existingData.isPresent()) {
        Clouduser oldUser = new Clouduser(existingData.get());
        Clouduser user = existingData.get();
        user.setName(updatedData.getName());  
        user.setDescription(updatedData.getDescription());
        user.setStatus(updatedData.getStatus());
        user.setCreatedAt(user.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now()); 
               
        Clouduser savedUser = clouduserRepository.save(user);

        logUpdate(savedUser,oldUser,"UPDATE");

        return clouduserRepository.save(user);
           
         }
          else {
        throw new RuntimeException("User not found with id: " + id);
        }
    }


    public Optional<Clouduser> getUserById(Long id) {  
        return clouduserRepository.findById(id); 
    }


    public void logDelete(Clouduser oldUser , String command){


        Map<String, Object> oldUserDetails = new HashMap<>();
        oldUserDetails.put("Name", oldUser.getName());
        oldUserDetails.put("Description", oldUser.getDescription());
        oldUserDetails.put("Status", oldUser.getStatus());
        JsonNode oldValue = objectMapper.valueToTree(oldUserDetails);

        Map<String, Object> ObjectIdentifire = new HashMap<>();
        ObjectIdentifire.put("name", oldUser.getName());
        ObjectIdentifire.put("data_Id",oldUser.getId());
        JsonNode Objectidentifire = objectMapper.valueToTree(ObjectIdentifire);

        LogEntity log = new LogEntity();
        log.setOldValue(oldValue);
        log.setOperation(command);
        log.setObjectType("/data");
        log.setCreated_by(oldUser.getName());
        log.setObjectIdentifier(Objectidentifire);
        log.setDataId(oldUser.getId());
        log.setTimestamp(LocalDateTime.now());
        dataRepository.save(log);
    }
   
    public void deleteUserbyId(Long id) {
        if (clouduserRepository.existsById(id)) {
            Optional<Clouduser> existingData = clouduserRepository.findById(id);
            clouduserRepository.deleteById(id);

            Clouduser oldUser = new Clouduser(existingData.get());


            logDelete(oldUser , "Delete");
        } else {
            throw new RuntimeException("Data not found for id: " + id);
        }
    }

}