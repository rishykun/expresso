package expresso;

/**
 * 
 */
public interface Expression {
    
    // Datatype definition
    //  Expression = Empty + NumberExp + OperationExp + ParenExp(Expression: e)
    //  NumberExp = IntegerExp(int n) + DecimalExp(double d)
    //  OperationExp = AddExp(Expression e1, Expression e2) + MultExp(Expression e1, Expressione2)

    /**
     * Parse an expression.
     * @param input expression to parse
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    
    // TODO Instance methods
      
}
