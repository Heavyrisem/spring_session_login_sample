package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.*;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .name("name")
                .password("password")
                .build();

        userService.createUser(createUserDto);
        User createdUser = userRepository.findByName(createUserDto.getName());

        assertNotNull("사용자 정보가 정상적으로 디비에 생성되었는지 체크", createdUser);
        assertEquals("입력한 아이디로 생성되었는지 체크", createUserDto.getName(), createdUser.getName());
    }
}