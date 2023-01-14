package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnAuthorizedException;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(CreateUserDto createUserDto) {
        // id: 123, pw: 109287391283719827318293
        User existUser = userRepository.findByName(createUserDto.getName());
        if (existUser != null) throw new ConflictException();

        User user = User.builder().name(createUserDto.getName()).password(createUserDto.getPassword()).build();
        user.setPassword(user.hashPassword());
        return userRepository.save(user);
    }

    public User verifyUser(LoginUserDto loginUserDto) {
        User user = userRepository.findByName(loginUserDto.getName());
        log.info("found user: {}", user);
        if (user == null) throw new UnAuthorizedException();

        boolean passwordValid = user.verifyPassword(loginUserDto.getPassword());
        if (!passwordValid) throw new UnAuthorizedException();

        return user;
    }
}
