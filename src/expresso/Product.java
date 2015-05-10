package expresso;

public class Product implements Expression{

    /**
     * Constructs the product of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
    
    private final Expression first;
    private final Expression second;
    
    public Product(Expression e1, Expression e2){
        first = e1;
        second = e2;
    }
    
    @Override
    public Expression add(Expression e) {
        Expression copyOfProduct = new Product(first, second);
        return new Sum(copyOfProduct, e);
    }

    @Override
    public Expression multiply(Expression e) {
        Expression copyOfProduct = new Product(first, second);
        return new Product(copyOfProduct, e);
    }

    @Override
    public Expression differentiate(Variable v) {
        Expression diffFirst = first.differentiate(v);
        Expression diffSecond = second.differentiate(v);
        Expression productFirst = new Product(first, diffSecond);
        Expression productSecond = new Product(second, diffFirst);
        
        return new Sum(productFirst, productSecond);
    }

    @Override
    public SimpleExpression simplify() {
        throw new RuntimeException("unimplemented");
    }
    
    @Override
    public String toString() {
        String parenthesizedFirst = "(" + first.toString() + ")";
        String parenthesizedSecond = "(" + second.toString() + ")";
        String product = parenthesizedFirst + " * " + parenthesizedSecond;
        return product;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Product)){return false;}
        Product otherExpression = (Product) other;
        return this.toString().equals(otherExpression.toString());
    }
    
    @Override
    public int hashCode(){
        return first.hashCode() * second.hashCode();
    }   

}
