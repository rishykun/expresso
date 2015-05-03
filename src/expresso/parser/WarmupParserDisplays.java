package expresso.parser;

import static org.junit.Assert.assertTrue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

public class WarmupParserDisplays {
    
    /**
     * This implementation of the ExpressionListener interface has methods that print out
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
        @Override public void enterBuild(ExpressionParser.BuildContext ctx) {
            System.err.println("entering build");
        }
        /**
         * {@inheritDoc}
         *
         * <p>Prints when a build is exited.</p>
         */
        @Override public void exitBuild(ExpressionParser.BuildContext ctx) {
            System.err.println("exiting build");
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
    }
    
    @Test
    public void displayEmptyString() {
        CharStream stream = new ANTLRInputStream("");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.line();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionListenerPrintEverything();
        walker.walk(listener, tree);
      //use of inspect method should be here, unsure of its syntax in reading 18
    }
    
    public void displaySingleBalanced() {
        CharStream stream = new ANTLRInputStream("()");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.line();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionListenerPrintEverything();
        walker.walk(listener, tree);
      //use of inspect method should be here, unsure of its syntax in reading 18
    }
    
    public void displayMultipleBalanced() {
        CharStream stream = new ANTLRInputStream("(()(()))");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.line();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionListenerPrintEverything();
        walker.walk(listener, tree);
        //use of inspect method should be here, unsure of its syntax in reading 18
    }

    public static void main(String[] args) {
        WarmupParserDisplays display = new WarmupParserDisplays();
        
        display.displayEmptyString();
        System.err.println("\n");
        display.displaySingleBalanced();
        System.err.println("\n");
        display.displayMultipleBalanced();
    }

}
