package expresso.parser.Expression;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expresso.parser.BalancedParenthesis.WarmupParserDisplays;
import expresso.parser.Expression.ExpressionParser.LonerContext;
import expresso.parser.Expression.ExpressionParser.TailContext;


public class ExpressionParserDisplay {
    /**
     * This implementation of the BalancedParenthesisListener interface has methods that print out
     * the action of a walker of a particular tree in order to compare the walker's actions
     * with the graph of the tree given by its inspect method.
     */
    private class ExpressionListenerPrintEverything implements ExpressionListener {
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a line is entered.</p>
         */
        @Override public void enterLine(ExpressionParser.LineContext ctx) {
            System.err.println("entering line");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a line is exited.</p>
         */
        @Override public void exitLine(ExpressionParser.LineContext ctx) {
            System.err.println("exiting line");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a build is entered.</p>
         */
        @Override public void enterExpression(ExpressionParser.ExpressionContext ctx) {
            System.err.println("entering Expression");
        }
        
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a build is exited.</p>
         */
        @Override public void exitExpression(ExpressionParser.ExpressionContext ctx) {
            System.err.println("exiting Expression");
        }

        /**
         * {@inheritDoc}
         *
         * <p>Prints when the walker begins entering every root</p>
         */
        @Override public void enterEveryRule(ParserRuleContext ctx) {
            System.err.println("entering every rule");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when the walker begins exiting every root</p>
         */
        @Override public void exitEveryRule(ParserRuleContext ctx) {
            System.err.println("exiting every rule");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a terminal is visited.</p>
         */
        @Override public void visitTerminal(TerminalNode node) {
            System.err.println("visiting terminal");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when an error node is visited.</p>
         */
        @Override public void visitErrorNode(ErrorNode node) {
            System.err.println("visiting error node");
        }
        @Override
        public void enterTail(TailContext ctx) {
            System.err.println("entering tail");
            
        }
        @Override
        public void exitTail(TailContext ctx) {
            System.err.println("exiting tail");
            
        }
        @Override
        public void enterLoner(LonerContext ctx) {
            System.err.println("entering loner");
            
        }
        @Override
        public void exitLoner(LonerContext ctx) {
            System.err.println("exiting loner");
            
        }
    }
    
    public void helperDisplay(String validExpression) {
        CharStream stream = new ANTLRInputStream(validExpression);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.line();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionListenerPrintEverything();
        walker.walk(listener, tree);
        System.err.println(tree.toStringTree(parser));
        ((RuleContext)tree).inspect(parser);
    }
    
    public void displaySimpleParenthesis() {
        helperDisplay("x*y*z");
        
    }
    
    public void displayComplexParenthesis() {
        helperDisplay("((x*y*z))+(((((54))))) + 3*x*y + 455");
        
    }
    
    public void displayNumber() {
        helperDisplay("123456789.012345");
        
    }
    
    
    public void displaySimpleOperation() {
        helperDisplay("(1+2)*((3))");
        
    }
    
    public void displayComplexOperation() {
        helperDisplay("a*b*c*d*1*2*3*4+5+6+7+8");
        
    }
    
    public void displayComplexExpression() {
        helperDisplay("a*b*c+d+e+f*g*h*i + ((((j)))) + ((k))*(((m))) + 5555.23 + dreams");
        
    }
    
    public static void main(String[] args) {
        ExpressionParserDisplay display = new ExpressionParserDisplay();
        display.displayComplexExpression();
    }

}
