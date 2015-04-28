package expresso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console interface to the expression system.
 */
public class Main {
    
    private static final String COMMAND_PREFIX = "!";
    
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
     * TODO
     */
    private static String handleExpression(String input) {
        throw new RuntimeException("unimplemented");
    }
    
    /**
     * TODO
     */
    private static String handleCommand(String substring) {
        throw new RuntimeException("unimplemented");
    }
    
}
