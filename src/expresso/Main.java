package expresso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console interface to the expression system.
 */
public class Main {
    
    private static final String COMMAND_PREFIX = "!";
    private static Expression currentExpression;
    private static final String invalidExpressionError = "ParseError: Please enter a valid expression!";
    private static final String invalidCommandError = "ParseError: Please enter a valid command!";
    
    /**
     * Read expression and command inputs from the console and output results.
     * An empty input terminates the program.
     * @param args unused
     * @throws IOException if there is an error reading the input
     */
    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print("> ");
            final String input = in.readLine();
            
            if (input.isEmpty()) {
                return;
            }
            
            try {
                final String output;
                if (input.startsWith(COMMAND_PREFIX)) {
                    output = handleCommand(input.substring(COMMAND_PREFIX.length()));
                } else {
                    output = handleExpression(input);
                }
                System.out.println(output);
            } catch (RuntimeException re) {
                System.out.println(re.getClass().getName() + ": " + re.getMessage());
            }
        }
    }
    
    /**
     * Outputs the expression typed in by the user, or an
     * error message if the expression is invalid
     * 
     * @param input an expression to perform command operations on
     * @return an equivalent representation of the expression, or an error 
     * if the expression is invalid
     */
    private static String handleExpression(String input) {
        try{
            currentExpression = Expression.parse(input);
            return currentExpression.toString();
        } 
        catch (RuntimeException e){
            return invalidExpressionError;
            
            
        }
       
        
    }
    
    /**
     * Outputs the result of a user command, or an
     * error message if the command is not recognized
     * 
     * @param substring a command to either simplify or differentiate the current polynomial
     * @return the result of applying the command, or an error if the command is incomplete or
     * not recognized
     */
    private static String handleCommand(String substring) {
        if (substring.equals("simplify")){
            Expression simplifiedExpression = currentExpression.simplify();
            currentExpression = simplifiedExpression;
            return simplifiedExpression.toString();
        } else if (substring.startsWith("d/d")){
            String variable = substring.substring(3, substring.length());
            Expression diffExpression = currentExpression.differentiate(new Variable(variable));
            currentExpression = diffExpression;
            return diffExpression.toString();
        } else {
            return invalidCommandError;
        }
        
    }
    
}
