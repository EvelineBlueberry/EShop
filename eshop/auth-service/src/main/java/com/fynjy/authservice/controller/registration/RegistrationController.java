package com.fynjy.authservice.controller.registration;

import com.fynjy.authservice.dto.RegistrationRequest;
import com.fynjy.authservice.dto.UserDto;
import com.fynjy.authservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userService;

    public static final String REGISTRATION = "/api/registration";

    @PostMapping(REGISTRATION)
    public UserDto signUp(@RequestBody RegistrationRequest request) {
        return userService.signUp(request);
    }


}
