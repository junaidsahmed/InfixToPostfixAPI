package com.service.convert.infixtopostfix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfixToPostfixRequest {

    String equation;
}
