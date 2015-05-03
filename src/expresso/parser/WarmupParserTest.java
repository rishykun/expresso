package expresso.parser;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.BeforeClass;
import org.junit.Test;

public class WarmupParserTest {
    
    /**
     * The input space of the parser was partitioned as follows:
     * 1) An empty String
     * 2) A single paren
     * 3) Opposite-facing parens )(
     * 4) A String with the number of left-facing parens not equal to the number of right-facing parens
     * 5) A balanced String of 1 left and right-facing paren ()
     * 6) A balanced String of more than 1 left and right-facing paren
     * 
     * The output space of the parser was partitioned as follows:
     * 1) No error in parsing the input string
     * 2) An error in parsing the input string
     * 
     * The method testParserEmptyString() covers input partition 1) and output partition 1)
     * The method testParserSingleParen() covers input partition 2) and output partition 2)
     * The method testParserOppositeParens() covers input partition 3) and output partition 2)
     * The method testParserUnequalParens() covers input partition 4) and output partition 2)
     * The method testParserSingleBalanced() covers input partition 5) and output partition 1)
     * The method testParserMultipleBalanced() covers input partition 6) and output partition 1)
     */

    @Test
    public void testParserEmptyString() {
        CharStream stream = new ANTLRInputStream("");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(false);
        }
    }
    
    @Test
    public void testParserSingleParen() {
        CharStream stream = new ANTLRInputStream("(");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testParserOppositeParens() {
        CharStream stream = new ANTLRInputStream(")(");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testParserUnequalParens() {
        CharStream stream = new ANTLRInputStream("(())((())");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testParserSingleBalanced() {
        CharStream stream = new ANTLRInputStream("()");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(false);
        }
    }
    
    @Test
    public void testParserMultipleBalanced() {
        CharStream stream = new ANTLRInputStream("(()((()))()(()))");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();
        try {
            TokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            ParseTree tree = parser.line();
        }catch (Exception e){
            assertTrue(false);
        }
    }
}