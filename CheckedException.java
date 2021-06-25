package project1_dailey_shawn;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author Shawn Dailey
 * Project: Expression Converter
 * Date: Week 2 
 * CheckedException class creates New exception case to be checked from main GUI
 */

public class CheckedException extends Exception {
    
    public CheckedException(String message, Throwable cause) {
            super(message, cause);
    }
    
    public CheckedException(Stack stack) throws CheckedException {
        try {
            stack.pop();
            if (!stack.isEmpty()) {
                throw new CheckedException(
                        "Error: Stack returned with too many elements", 
                        this
                );
            }
	} catch (EmptyStackException e) {
            throw new CheckedException("Error: Stack returned empty.", e);
	}
    }
    
    public String toString() {
         return this.getClass().getSimpleName() + " : Stack Error";
    }
}
