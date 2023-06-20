package com.pangong.fullstackbackendpost;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FullstackBackendPostApplicationTests {
	Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	void itShouldAddTwoNumbers(){
		//given
		int firstNum = 10;
		int secondNum = 20;

		//when
		int result = calculator.add(firstNum,secondNum);

		//then
		int expectedNum = 30;
		assertThat(result).isEqualTo(expectedNum);
	}

	class Calculator{
		int add(int a, int b){
			return a + b;
		}
	}

}
