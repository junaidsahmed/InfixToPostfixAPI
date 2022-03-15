package com.service.convert.infixtopostfix.controller;

import com.service.convert.infixtopostfix.dto.ErrorRespose;
import com.service.convert.infixtopostfix.dto.InfixToPostfixRequest;
import com.service.convert.infixtopostfix.dto.InfixToPostfixResponse;
import com.service.convert.infixtopostfix.service.implementation.InfixToPostfixHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@RestController
@RequestMapping(value = "/v1")
public class InfixToPostfixController {
    @Autowired
    InfixToPostfixHandler infixToPostfixHandler;

    @PostMapping(value = "infixtopostfix/convert",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  public  ResponseEntity<?> getInfixToPostfixOperation(@RequestBody InfixToPostfixRequest infixToPostfixRequest){
        InfixToPostfixResponse infixToPostfixResponse = new InfixToPostfixResponse();
        try {
            String postfixExp=infixToPostfixHandler.convertInfixToPostfix(infixToPostfixRequest.getEquation());
            infixToPostfixResponse.setResult(infixToPostfixHandler.calculatePostfixExpression(postfixExp));
            infixToPostfixResponse.setPostfix(postfixExp);
            return ResponseEntity.ok(infixToPostfixResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorRespose(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }
}
