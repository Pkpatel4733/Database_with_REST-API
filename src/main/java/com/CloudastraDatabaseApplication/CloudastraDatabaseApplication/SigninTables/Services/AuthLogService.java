package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Services;

import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Model.UserAuthLog;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Repository.UserAuthLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthLogService {

    private final UserAuthLogRepository logRepository;

    public AuthLogService(UserAuthLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(Long userId, String username, String actionType, String status, String message, HttpServletRequest request) {
        UserAuthLog log = new UserAuthLog();
        log.setUserId(userId);
        log.setUsername(username);
        log.setActionType(actionType);
        log.setStatus(status);
        log.setMessage(message);
        log.setUserAgent(request.getHeader("User-Agent"));
        logRepository.save(log);
    }
}
