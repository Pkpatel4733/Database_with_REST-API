package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Model.User;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Repository.UserRepository;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.SigninTables.Services.AuthLogService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Login")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthLogService authLogService;

    @PostMapping("/Signin")
    public ResponseEntity<String> registerOrLogin(@RequestBody User request, HttpServletRequest httpRequest) {
        String username = request.getUsername();
        String password = request.getPassword();

        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            // User exists, check password
            User user = existingUser.get();
            if (user.getPassword().equals(password)) {
                authLogService.log(user.getId(), username, "login", "success", "Login successful", httpRequest);
                return ResponseEntity.ok("Login successful");
            } else {
                authLogService.log(null, username, "login_failed", "fail", "Incorrect password", httpRequest);
                return ResponseEntity.status(401).body("Invalid password");
            }
        } else {
            // User does not exist, register them
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password); // plain text for demo
            userRepository.save(newUser);

            authLogService.log(newUser.getId(), username, "register", "success", "User registered and logged in", httpRequest);
            return ResponseEntity.ok("User registered successfully and logged in");
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody User request, HttpServletRequest httpRequest) {
        String username = request.getUsername();

        // Check if the user exists
        return userRepository.findByUsername(username)
                .map(user -> {
                    authLogService.log(user.getId(), username, "logout", "success", "User logged out", httpRequest);
                    return ResponseEntity.ok("Logout successful");
                })
                .orElseGet(() -> {
                    authLogService.log(null, username, "logout_failed", "fail", "Logout attempt for unknown user", httpRequest);
                    return ResponseEntity.status(404).body("User not found");
                });
    }


}
