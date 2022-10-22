package com.fynjy.authservice.service;

import com.fynjy.authservice.dto.RegistrationRequest;
import com.fynjy.authservice.dto.UserDto;
import com.fynjy.authservice.factory.UserDtoFactory;
import com.fynjy.authservice.model.UserEntity;
import com.fynjy.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoFactory userDtoFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found!!")
        );
    }


    public UserDto signUp(RegistrationRequest request) {

        userRepository.findByUsername(request.getUsername()).ifPresent(
                it -> {throw new RuntimeException("User with username " + request.getUsername() + " already exists");}
        );
        userRepository.findByEmail(request.getEmail()).ifPresent(
                it -> {throw new RuntimeException("User with email " + request.getEmail() + " already exists ;<");}
        );

        UserEntity user = userRepository.saveAndFlush(
                UserEntity
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .gender(request.getGender())
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build());

        return userDtoFactory.makeUserDto(user);
    }
}
