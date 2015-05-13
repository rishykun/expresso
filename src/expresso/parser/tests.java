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
import expresso.parser.Expression.OurExpressionListener;

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
//        CharStream stream = new ANTLRInputStream("x * ((x + y) * y)");
//        ExpressionLexer lexer = new ExpressionLexer(stream);
//        TokenStream tokens = new CommonTokenStream(lexer);
//        ExpressionParser parser = new ExpressionParser(tokens);
//        ParseTree tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker = new ParseTreeWalker();
//        OurExpressionListener listener = new OurExpressionListener();
//        walker.walk(listener, tree);
//        Expression result = listener.getExpression();
//        System.out.println(result.toString());
//        
//        stream = new ANTLRInputStream("x * x + x");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker2 = new ParseTreeWalker();
//        OurExpressionListener listener2 = new OurExpressionListener();
//        walker2.walk(listener2, tree);
//        Expression result2 = listener2.getExpression();
//        System.out.println(result2.toString());
//        
//        stream = new ANTLRInputStream("x + x * x");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker3 = new ParseTreeWalker();
//        OurExpressionListener listener3 = new OurExpressionListener();
//        walker3.walk(listener3, tree);
//        Expression result3 = listener3.getExpression();
//        System.out.println(result3.toString());
//        
//        stream = new ANTLRInputStream("x * y + x + y");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker4 = new ParseTreeWalker();
//        OurExpressionListener listener4= new OurExpressionListener();
//        walker4.walk(listener4, tree);
//        Expression result4 = listener4.getExpression();
//        System.out.println(result4.toString());
//        
//        stream = new ANTLRInputStream("x * x + x * x");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker5 = new ParseTreeWalker();
//        OurExpressionListener listener5 = new OurExpressionListener();
//        walker5.walk(listener5, tree);
//        Expression result5 = listener5.getExpression();
//        System.out.println(result5.toString());
//        
//        stream = new ANTLRInputStream("x * x * x + 1");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker6 = new ParseTreeWalker();
//        OurExpressionListener listener6 = new OurExpressionListener();
//        walker6.walk(listener6, tree);
//        Expression result6 = listener6.getExpression();
//        System.out.println(result6.toString());
//        
//        stream = new ANTLRInputStream("x + (y*z) + (4*(z+z))");
//        lexer = new ExpressionLexer(stream);
//        tokens = new CommonTokenStream(lexer);
//        parser = new ExpressionParser(tokens);
//        tree = parser.line();
//        ((RuleContext)tree).inspect(parser);
//        ParseTreeWalker walker7 = new ParseTreeWalker();
//        OurExpressionListener listener7 = new OurExpressionListener();
//        walker7.walk(listener7, tree);
//        Expression result7 = listener7.getExpression();
//        System.out.println(result7.toString());
    }

}
