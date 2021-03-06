package expresso.parser.Expression;

import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expresso.Empty;
import expresso.Expression;
import expresso.Number;
import expresso.Product;
import expresso.Sum;
import expresso.Variable;

/**
 * This class implements the ExpressionListener generated by ANTLR
 * and extends ExpressionBaseListener in order to parse arbitrary valid input Expressions.
 */
public class OurExpressionListener extends ExpressionBaseListener{
    
    private Stack<Expression> stack = new Stack<Expression>();
    private Stack<String> operations = new Stack<String>();
    private Stack<Integer> nestLevel = new Stack<Integer>();
    private boolean ignoreLoner = false;
    private int level = 0;
    
    /**
     * {@inheritDoc}
     *
     * <p>Does nothing when entering a line.</p>
     */
    @Override public void enterLine(ExpressionParser.LineContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>Checks to see that the stack is left with a single Expression
     * and that no operations are left when exiting a line.</p>
     */
    @Override public void exitLine(ExpressionParser.LineContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>Does nothing when entering an Expression.</p>
     */
    @Override public void enterExpression(ExpressionParser.ExpressionContext ctx) {
        if (ctx.parent.getChildCount() == 4 && ctx.getChildCount() == 4){
            level++;
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>When exiting an expression, uses the last operation pushed to combine its 
     * appropriate children, or pushes its left child if nothing is to be combined.</p>
     */
    @Override public void exitExpression(ExpressionParser.ExpressionContext ctx) {
        if (ctx.tail().getChildCount() == 3 && stack.peek().equals(new Empty())){
            stack.pop();
        }
        if ((stack.size() > 1)) {
            Expression rightChild = stack.pop();
            Expression leftChild = stack.pop();
            if (!rightChild.equals(new Empty())){
                if (!operations.isEmpty() && !nestLevel.isEmpty()
                        && nestLevel.peek().equals(new Integer(level))){
                    String op = operations.pop();
                    nestLevel.pop();
                    if (op.equals("*")){
                        Expression product = new Product(leftChild, rightChild);
                        stack.push(product);
                    }else{
                        Expression sum = new Sum(leftChild, rightChild);
                        stack.push(sum);
                    }
                }else{
                    stack.push(leftChild);
                    stack.push(rightChild);
                }
            }else{
                stack.push(leftChild);
            }
            if (ctx.parent.getChildCount() == 4
                    && stack.size() > 1
                    && !operations.isEmpty()
                    && !nestLevel.isEmpty()
                    && operations.peek().equals("*")
                    && nestLevel.peek().equals(new Integer(level))){
                operations.pop();
                nestLevel.pop();
                Expression groupedPart = stack.pop();
                Expression toBeMultiplied = stack.pop();
                stack.push(new Product(toBeMultiplied, groupedPart));
            }
        }
        if (ctx.getChildCount() == 4 && ctx.parent.getChildCount() == 4){
            level--;
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>When entering a non-empty tail, gives precedence to ungrouped multiplication over ungrouped addition</p>
     */
    @Override public void enterTail(ExpressionParser.TailContext ctx) {
        if (ctx.getChildCount() == 3 
                && ctx.getChild(0).getText().equals("*")
                && ctx.getChild(1).getChildCount() != 4){
            Expression toBeMultiplied = stack.pop();
            String value = ctx.expression().loner().getChild(0).getText();
            if (value.matches("[a-zA-z]+")){
                stack.push(new Product(toBeMultiplied, new Variable(value)));
            }else{
                try{
                    int integerVal = Integer.parseInt(value);
                    stack.push(new Product(toBeMultiplied, new Number(integerVal)));
                }catch (NumberFormatException e){
                    double doubleVal = Double.parseDouble(value);
                    stack.push(new Product(toBeMultiplied, new Number(doubleVal)));
                }
            }
            ignoreLoner = true;
        }else if (ctx.getChildCount() == 3){
            operations.push(ctx.getChild(0).getText());
            nestLevel.push(level);
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>When exiting an empty tail, adds Empty() to the stack to represent nothing
     * (will be removed).</p>
     */
    @Override public void exitTail(ExpressionParser.TailContext ctx) {
        if (ctx.getChildCount() == 0){
            stack.push(new Empty());
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>Does nothing when entering a loner.</p>
     */
    @Override public void enterLoner(ExpressionParser.LonerContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>Adds either a number or variable to the stack when exiting a loner.</p>
     */
    @Override public void exitLoner(ExpressionParser.LonerContext ctx) {
        if (!ignoreLoner){
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
        }else{
            ignoreLoner = false;
        }
    }
    
    /**
     * Returns the Expression that this tree walker created after completing its walk along the tree.
     * 
     * @return an Expression representing a client's input String
     */
    public Expression getExpression(){
        return stack.get(0);
    }
}
