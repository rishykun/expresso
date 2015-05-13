package expresso;

public class Empty implements Expression {

    @Override
    public Expression add(Expression e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Expression multiply(Expression e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Expression differentiate(Variable v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SimpleExpression simplify() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean equals(Object other){
        if (other instanceof Empty){
            return true;
        }
        return false;
    }
}
