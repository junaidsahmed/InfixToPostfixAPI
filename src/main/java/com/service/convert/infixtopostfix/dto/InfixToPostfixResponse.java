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
@AllArgsConstructor
@NoArgsConstructor
public class InfixToPostfixResponse {
        private String postfix;
        private Integer result;
}
