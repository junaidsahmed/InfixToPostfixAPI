package com.service.convert.infixtopostfix.service.implementation;

import com.service.convert.infixtopostfix.exception.PostfixToInfixException;
import com.service.convert.infixtopostfix.service.interfaces.ExpressionConverterInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@Service
@Slf4j
public class InfixToPostfixHandler implements ExpressionConverterInterface {

    /*
    this function converts infix expression to  postfix expression
    * */
    @Override
    public String convertInfixToPostfix(String expression) {
        Stack<Character> stack = new Stack();
        StringBuilder postfix= new StringBuilder();
        for(int i=0 ;i<expression.trim().length();i++){
            if(ExpressionConverterInterface.isOperator(expression.charAt(i))){
                while (!stack.isEmpty() && stack.peek()!='(' && ExpressionConverterInterface.getPrecedence(stack.peek(),expression.charAt(i))){
                    postfix.append(stack.pop());
                }
                stack.push(expression.charAt(i));
            }
            else if(ExpressionConverterInterface.isOperand(expression.charAt(i))){
                    postfix.append(expression.charAt(i));
            }
            else if(expression.charAt(i)=='('){
                stack.push(expression.charAt(i));

            }
            else if(expression.charAt(i)==')'){
                while (!stack.isEmpty()&& stack.peek()!='('){
                        postfix.append(stack.peek());
                        stack.pop();
                }
                stack.pop();
            }


        }
            while (!stack.empty()){
                postfix.append(stack.peek());
                stack.pop();
            }
            String result=postfix.toString();
            return result.replaceAll(".(?!$)", "$0 ");
    }
    /*
    this function will validate and calculate postfix expression
    * */
    public  Integer calculatePostfixExpression(String expression) throws Exception {
        Stack<Integer> stack= new Stack();
        int value; String operation;
        int op1,op2;
        int result=0;
        Scanner token = new Scanner(expression);
        while (token.hasNext()){
            if(token.hasNextInt()){
                value = token.nextInt();
                stack.push(value);
            }
            else {
                operation=token.next();
                if(stack.isEmpty())
                    throw new PostfixToInfixException("Not enough operands");
                op2=stack.peek();
                stack.pop();
                if(stack.isEmpty())
                    throw new PostfixToInfixException("Not enough operands");
                op1= stack.peek();
                stack.pop();

                result= ExpressionConverterInterface.performOperation(operation.charAt(0),op1,op2);
                stack.push(result);
            }
        }
        if(stack.isEmpty())
            throw new PostfixToInfixException("Not enough operands");
        result=stack.peek();
        stack.pop();
        if (!stack.isEmpty())
            throw new PostfixToInfixException("Too Many Operands");
        return result;
    }


    public static void main(String[] args) throws Exception {
        InfixToPostfixHandler infix = new InfixToPostfixHandler();
        String e1="4*(5-(7+2))";
        String e2="((3+4)*2)/7";
        String e3="(5+6)*(6-2)";
        String e4="(4+2)+(3*(5-1))";
       // System.out.println(infix.convertInfixToPostfix(e1));
//        String operators="+-/*";

        String converted =infix.convertInfixToPostfix(e1);
                System.out.println(converted);
        System.out.println(infix.calculatePostfixExpression(converted));

    }
}
