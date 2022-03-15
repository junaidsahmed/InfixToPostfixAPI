package com.service.convert.infixtopostfix.service;

import com.service.convert.infixtopostfix.service.implementation.InfixToPostfixHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@SpringBootTest
public class InfixToPostfixHandlerTest {

    @InjectMocks
    InfixToPostfixHandler infixToPostfixHandler;


    @Test
    public void convertInfixToPostfixTest(){
        String infixExpression="4*(5-(7+2))";
        Assertions.assertEquals(infixToPostfixHandler.convertInfixToPostfix(infixExpression),"4 5 7 2 + - *");
    }

    @Test
    public void calculatePostfixExpression() throws Exception {
        String postfixExpression="4 5 7 2 + - *";
        Assertions.assertEquals(infixToPostfixHandler.calculatePostfixExpression(postfixExpression),-16);
    }
}
