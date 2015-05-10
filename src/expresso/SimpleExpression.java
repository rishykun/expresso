package expresso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleExpression implements Expression {

    private Monomial leading;
    private SimpleExpression remaining;
    private boolean isMonomial = false;
    
    public SimpleExpression(Monomial m, SimpleExpression e){
        leading = m;
        remaining = e;
    }
    
    public SimpleExpression(){};
    
    @Override
    public Expression add(Expression e) {
        return leading.add(remaining).add(e);
    }
    
    public SimpleExpression simpleAdd(Monomial m){
        return new SimpleExpression(m, this);
    }

    @Override
    public Expression multiply(Expression e) {
        return leading.add(remaining).multiply(e);
    }

    @Override
    public Expression differentiate(Variable v) {
        return leading.add(remaining).differentiate(v);
    }

    @Override
    public SimpleExpression simplify() {
        return this;
    }
    
    public boolean checkMonomial() {
        return isMonomial;
    }

    public Iterator<Monomial> iterator(){
        List<Monomial> listOfTerms = new ArrayList<Monomial>();
        SimpleExpression currentExpression = this;
        
        while (!currentExpression.checkMonomial()){
            Monomial currentLeading = currentExpression.leading();
            listOfTerms.add(0, currentLeading);
            currentExpression = currentExpression.truncate();
        }
        Monomial lastMonomial = currentExpression.leading();
        listOfTerms.add(0, lastMonomial);
        return listOfTerms.iterator();
        
    }
    
    public Monomial leading(){
        return leading;
    }
    
    public SimpleExpression truncate(){
        return remaining;
    }
    
    @Override
    public String toString(){
        String out = leading.toString() + "+" + remaining.toString();
        if (out.endsWith("+0")){
            out = out.substring(0, out.length()-2);
        }
        return out;
    }
    
    public static void main(String[] args){
        SimpleExpression e1 = new Variable("x").multiply(new Variable("y")).add(new Variable("y").multiply(new Variable("x")).multiply(new Variable("y"))).simplify();
        System.out.println(e1);
        
    }
}
