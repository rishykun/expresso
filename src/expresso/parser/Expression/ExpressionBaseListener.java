// Generated from Expression.g4 by ANTLR 4.5

package expresso.parser.Expression;


import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expresso.Expression;
import expresso.Number;
import expresso.Product;
import expresso.Sum;
import expresso.Variable;

/**
 * This class provides an empty implementation of {@link ExpressionListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class ExpressionBaseListener implements ExpressionListener {
    
    private Stack<Expression> stack = new Stack<Expression>();
    private Stack<String> operations = new Stack<String>();
    
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLine(ExpressionParser.LineContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLine(ExpressionParser.LineContext ctx) {
	    assert stack.size() == 1;
	    assert operations.size() == 0;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExpression(ExpressionParser.ExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitExpression(ExpressionParser.ExpressionContext ctx) {
	    if (ctx.tail().getChildCount() == 3 && stack.peek().equals(new Number(0))){
	        stack.pop();
	    }
	    Expression rightChild = stack.pop();
	    Expression leftChild = stack.pop();
	    if (!rightChild.equals(new Number(0))){
	        String op = operations.pop();
	        if (op.equals("*")){
	            Expression product = new Product(leftChild, rightChild);
	            stack.push(product);
	        }else{
	            Expression sum = new Sum(leftChild, rightChild);
	            stack.push(sum);
	        }
	    }else{
	        stack.push(leftChild);
	    }
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterTail(ExpressionParser.TailContext ctx) {
	    if (ctx.getChildCount() == 3){
	        operations.push(ctx.getChild(0).getText());
	    }
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitTail(ExpressionParser.TailContext ctx) {
	    if (ctx.getChildCount() == 0){
	        stack.push(new Number(0));
	    }
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLoner(ExpressionParser.LonerContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLoner(ExpressionParser.LonerContext ctx) {
	    String value = ctx.getChild(0).getText();
	    if (value.matches("[a-zA-z]+")){
	        Expression variable = new Variable(value);
	        stack.push(variable);
	    }else{
	        try{
	            int integerVal = Integer.parseInt(value);
	            Expression number = new Number(integerVal);
	            stack.push(number);
	        }catch (NumberFormatException e){
	            double doubleVal = Double.parseDouble(value);
	            Expression number = new Number(doubleVal);
	            stack.push(number);
	        }
	    }
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
	
	/**
	 * Returns the Expression that this tree walker created after completing its walk along the tree.
	 * 
	 * @return an Expression representing a client's input String
	 */
	public Expression getExpression(){
	    return stack.get(0);
	}
}