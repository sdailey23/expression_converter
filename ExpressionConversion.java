package project1_dailey_shawn;

import java.util.*;

/**
 * @author Shawn Dailey
 * Project: Expression Converter
 * Date: Week 2 
 * Class evaluates user inputs from main and converts prefix and postfix expressions
 */

public class ExpressionConversion {   
    
    // Evaluates & ensures user input formatted for conversion
    private StringTokenizer formatTokens(String s) {
        // evaluate stack for joined operators and white space
        String[] tokens = s.split("(?<=[-+*/^,])|(?=[-+*/^,])");
        
        // convert array to string & removed all delimiters
        s = "";
        for (String token : tokens) {
            if (!token.isBlank() 
                    && !token.trim().equals(",") 
                    && !token.trim().equals("[") 
                    && !token.trim().equals("]")) {
                s += token + " ";
            }
        }
        
        return new StringTokenizer(s, " ", false);
    }
    
    // Accepts prefix expression from user & converts to postfix expression
    public Stack getPostfix(String expression) throws EmptyStackException {
        Stack revStack = new Stack();
        Stack operandStack = new Stack();
        
        // tokenize string containing prefix expression
        StringTokenizer preTokenizer = formatTokens(expression);
        
        // while there are more tokens: push the token onto a reversal stack if it is not a space
        while (preTokenizer.hasMoreTokens()) {
            revStack.push(preTokenizer.nextToken(" "));
        }
        
        // while the reversal stack is not empty: pop the next token from the reversal stack
        while(!revStack.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            String token = revStack.pop().toString().trim();
            
            // if it is an operand: push it onto the operand stack
            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(token);
            } else {
            // else it is an operator:
                // pop two operands off of the operand stack
                // create a string with the two operands followed the operator
                String lft = operandStack.pop().toString().trim();
                String rt = operandStack.pop().toString().trim();
                // push that string onto the operand stack
                operandStack.push(
                    builder
                        .append(lft).append(" ")
                        .append(rt).append(" ")
                        .append(token).append(" ")
                );
            }
        }
        
        return operandStack;
    }
    
    // Accepts postfix expression from user & converts to prefix expression
    public Stack getPrefix(String expression) throws EmptyStackException {
        Stack operandStack = new Stack();
        
        // tokenize string containing postfix expression
        StringTokenizer postTokenizer = formatTokens(expression);
        
        // while there are more tokens get the next token if it is not a space
        while (postTokenizer.hasMoreTokens()) {
            StringBuilder builder = new StringBuilder();
            String token = postTokenizer.nextToken(" ").trim();
            
            // if it is an operand: push it onto the operand stack
            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(token.trim());
            } else {
            // else it is an operator:
                // pop two operands off of the operand stack
                // create a string with the operator followed by two operands
                String rt = operandStack.pop().toString().trim();
                String lft = operandStack.pop().toString().trim();
                // push that string onto the operand stack
                operandStack.push(
                    builder
                        .append(token).append(" ")
                        .append(lft).append(" ")
                        .append(rt).append(" ")
                );
            }
        }
        
        return operandStack;
    }
    
}
