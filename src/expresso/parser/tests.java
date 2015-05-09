package expresso.parser;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import expresso.parser.BalancedParenthesis.BalancedParenthesisLexer;
import expresso.parser.BalancedParenthesis.BalancedParenthesisParser;
import expresso.parser.Expression.ExpressionLexer;
import expresso.parser.Expression.ExpressionListener;
import expresso.parser.Expression.ExpressionParser;
import expresso.Expression;
import expresso.parser.Expression.ExpressionBaseListener;

public class tests {
    
    
    @Test
    public void simpleTest() {
        CharStream stream = new ANTLRInputStream("(())");
        BalancedParenthesisLexer lexer = new BalancedParenthesisLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        BalancedParenthesisParser parser = new BalancedParenthesisParser(tokens);
        ParseTree tree = parser.line();   
    }
    
    //fiddling
    public static void main(String[] args){
        CharStream stream = new ANTLRInputStream("1       +    x + 2.2 + x*y +xy+0.1");
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.line();
        ((RuleContext)tree).inspect(parser);
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionBaseListener listener = new ExpressionBaseListener();
        walker.walk(listener, tree);
        Expression result = listener.getExpression();
        System.out.println(result.toString());
        
//        stream = new ANTLRInputStream("x + (y*z) + (4*z*z)");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        
//        stream = new ANTLRInputStream("x + (y*z) + (4*(z+z))");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
    }

}
