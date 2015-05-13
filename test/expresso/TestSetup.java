package expresso;

public class TestSetup {
    
    //basic expressions
    protected static Number one;
    protected static Number zero;
    protected static Number two;
    protected static Number onepointone;
    protected static Expression oneplusone;
    protected static Expression onetimesone;
    protected static Variable x;
    protected static Variable y;
    protected static Variable z;
    protected static Variable alongname;
    protected static Product twox;
    protected static Product twoplusxtimesxplusy;
    protected static Product twotimesxtimesxtimesy;
    protected static Product nestedproductandsumtwoxy;
    protected static Product xy;
    protected static Sum xplusy;
    protected static Sum xplusypluszplusone;
    protected static Expression xplusone;
    protected static Monomial xtimesx;
    protected static SimpleExpression xtimesxplusx;
    
    //slightly more complicated expressions
    protected static Expression xplusytimesxy;
    protected static Expression xytimesxplusy;
    protected static Expression xplusyplusxy;
    protected static Expression xyplusxplusy;
    protected static Expression dxy;
    protected static Expression dxplusy;
    
    public static void setup(){
        x = new Variable("x");
        y = new Variable("y");
        z = new Variable("z");
        one = new Number(1);
        zero = new Number(0);
        two = new Number(02.0);
        twox = new Product(two, x);
        alongname = new Variable("alongname");
        twoplusxtimesxplusy = new Product(new Sum(two, x), new Sum(x, y));
        twotimesxtimesxtimesy = new Product(new Product(two, x), new Product(x, y));
        nestedproductandsumtwoxy = new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x));
        xy = new Product(x,y);
        xplusy = new Sum(x,y);
        xplusypluszplusone = new Sum(x, new Sum(y, new Sum(z, one)));
        xplusone = x.add(one);
        xtimesx = x.multiply(x);
        xtimesxplusx = new SimpleExpression(xtimesx, x);
        
        xplusytimesxy = Expression.parse("(x+y)*(x*y)"); 
        xytimesxplusy = Expression.parse("(x*y)*(x+y)");
        xplusyplusxy = Expression.parse("(x+y)+(x*y)");
        xyplusxplusy = Expression.parse("(x*y)+(x+y)");
        dxy = xy.differentiate(x);
        dxplusy = xplusy.differentiate(x);
        
        oneplusone = Expression.parse("1+1");
        onetimesone = Expression.parse("1*1");
        onepointone = new Number(001.100);
    }
}
