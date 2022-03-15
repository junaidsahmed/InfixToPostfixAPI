package com.service.convert.infixtopostfix.service.interfaces;

import com.service.convert.infixtopostfix.exception.PostfixToInfixException;
import org.springframework.stereotype.Service;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@Service
public interface ExpressionConverterInterface {

    String convertInfixToPostfix(String expression);
    static int performOperation(char operation, int a, int b) throws Exception {
        switch (operation)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
            default:
                throw new PostfixToInfixException("Invalid expression");
        }
    }

    static boolean getPrecedence(char op1, char op2){
        int op1Weight = getOperatorWeight(op1);
        int op2Weight = getOperatorWeight(op2);
        if (op1Weight == op2Weight){
            return true;
        }
        return op1Weight > op2Weight;
    }

    public static int getOperatorWeight(char op){
        int weight = -1;
        switch (op)
        {
            case '+':
            case '-':
                weight = 1;
                break;
            case '*':
            case '/':
            case '%':
                weight = 2;
        }
        return weight;
    }

    static boolean isOperand(char C){
        if (C >= '0' && C<='9')
            return true;
        return false;
    }


    static boolean isOperator(char operator){
        String operators="+-/*";
        return operators.indexOf(operator) != -1;
    }


}
