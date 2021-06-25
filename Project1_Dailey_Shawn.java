package project1_dailey_shawn;

import java.awt.*;
import java.awt.event.*;
import java.util.EmptyStackException;
import javax.swing.*;

/**
 * @author Shawn Dailey
 * Project: Expression Converter
 * Date: Week 2 
 * Project Description: Project consist of GUI to accept user inputs to be 
 * translate from prefix to postfix expressions and postfix to prefix expressions
 */

public class Project1_Dailey_Shawn {

    public static void main(String[] args) {
        
        ExpressionConversion ec = new ExpressionConversion();
        
        // MAIN WINDOW SETUP
        JFrame window = new JFrame("Expression Converter");
        JPanel main = new JPanel(); //creates "recycalable" window w/ border
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        
        // setup user input block
        JPanel userInputContainer = new JPanel();
        JLabel inputLabel = new JLabel("Enter Expression");
        JTextField inputBlock = new JTextField();
        inputBlock.setPreferredSize(new Dimension( 200, 20 ));
        
        userInputContainer.setLayout(new BoxLayout(userInputContainer, BoxLayout.X_AXIS));
        userInputContainer.setAlignmentY(userInputContainer.CENTER_ALIGNMENT);
        userInputContainer.add(inputLabel);
        userInputContainer.add(inputBlock);

        main.add(userInputContainer);
        
        // setup action buttons
        JPanel buttonsContainer = new JPanel();
        JButton toPost = new JButton("Prefix to Postfix");
        JButton toPre = new JButton("Postfix to Prefix");
        
        buttonsContainer.setLayout(new GridLayout(1, 2));
        buttonsContainer.setAlignmentY(userInputContainer.CENTER_ALIGNMENT);
        buttonsContainer.add(toPost);
        buttonsContainer.add(toPre);
        
        main.add(buttonsContainer);
        
        // setup result block
        JPanel resultContainer = new JPanel();
        JLabel resultLabel = new JLabel("Result ");
        JTextField result = new JTextField();
        
        result.setEditable(false);
        result.setOpaque(false);
        result.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
        
        resultContainer.setLayout(new BoxLayout(resultContainer, BoxLayout.X_AXIS));
        resultContainer.setAlignmentY(resultContainer.CENTER_ALIGNMENT);
        resultContainer.add(resultLabel);
        resultContainer.add(result);
        
        main.add(resultContainer);
        
        // TODO: edit button actions
        toPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                String input = inputBlock.getText().trim();
                
                // Checks user prefix input & shows output expression
                try {
                    new CheckedException(ec.getPostfix(input));
                    result.setText(ec.getPostfix(input).pop().toString().trim());
                    result.setVisible(true);
                } catch (CheckedException c) {
                        JOptionPane.showMessageDialog(
                            window, 
                            c.getMessage(),
                            c.toString(), 
                            JOptionPane.ERROR_MESSAGE
                        );
                } catch (EmptyStackException c) {
                        JOptionPane.showMessageDialog(
                            window, 
                            "Stack error: Returned empty stack",
                            c.getClass().getSimpleName(), 
                            JOptionPane.ERROR_MESSAGE
                        );
                }
            }
        });
        
        // TODO: edit button actions
        toPre.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("");
                String input = inputBlock.getText().trim();
                
                // Checks user postfix input & shows output expression
                try {
                    new CheckedException(ec.getPrefix(input));
                    result.setText(ec.getPrefix(input).pop().toString().trim());
                    result.setVisible(true);
                } catch (CheckedException c) {
                        JOptionPane.showMessageDialog(
                            window, 
                            c.getMessage(),
                            c.toString(), 
                            JOptionPane.ERROR_MESSAGE
                        );
                } catch (EmptyStackException c) {
                        JOptionPane.showMessageDialog(
                            window, 
                            "Stack error: Returned empty stack",
                            c.getClass().getSimpleName(), 
                            JOptionPane.ERROR_MESSAGE
                        );
                }
            }
        });

        // Initial window constraints
        window.getContentPane().add(main);
        center(window);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    // Other
    private static void center(Window window) {
        // Centers windown on screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
        window.setLocation(x, y);
    }
    
}
