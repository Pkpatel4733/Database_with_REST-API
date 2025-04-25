package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Repository;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Model.UserAuthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthLogRepository extends JpaRepository<UserAuthLog, Long> {

}