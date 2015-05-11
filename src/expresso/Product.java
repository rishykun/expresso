package expresso;

public class Product implements Expression{
    
    private final Expression first;
    private final Expression second;
    
    //Abstraction function
    //Rep Invariant
    //Rep Exposure
    
    /**
     * Constructs the product of two specified expressions
     * @param e1 first expression
     * @param e2 second expression
     */
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
        System.out.println("Simplifying " + this.toString());
        SimpleExpression first = this.first.simplify();
        SimpleExpression second = this.second.simplify();
        Expression summand = new Number(0);
        if (second.checkMonomial()){
            SimpleExpression secondCopy = second;
            second = first; 
            first = secondCopy;
        }
        if (first.checkMonomial()){
            Monomial multiplier = first.leading();
            for (Monomial m:second){
                summand = summand.add(m.multiply(multiplier));
            }
            SimpleExpression s = summand.simplify();
            System.out.println("Simplified " +this.toString() + "to");
            System.out.println(s);
            return s;
        }
        else {
            SimpleExpression s = (new Sum(first.leading().multiply(second), first.truncate().multiply(second))).simplify();
            System.out.println("Simplified " +this.toString() + "to");
            System.out.println(s);
            return s;
            //return (new Sum(first.leading().multiply(second), first.truncate().multiply(second))).simplify();
        }
        
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
