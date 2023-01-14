package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		Integer num = 1;
		boolean bool = false;

		assertEquals("num은 1 이여야 한다", 1, num);
		assertFalse("bool이 false인지 검사", bool);
	}
}
