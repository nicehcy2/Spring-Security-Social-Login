package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.User;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        if (userRepository.existsByUsername(joinDTO.getUsername()))
            return;

        User savedUser = User.builder()
                .username(joinDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .role("ROLE_ADMIN")
                .build();

        userRepository.save(savedUser);
    }
}
