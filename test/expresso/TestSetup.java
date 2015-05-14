package expresso;

public class TestSetup {
    
    protected static Number one;
    protected static Number zero;
    protected static Number two;
    protected static Number four;
    protected static Number onepointone;
    protected static Expression oneplusone;
    protected static Expression onetimesone;
    protected static Variable x;
    protected static Variable y;
    protected static Variable z;
    protected static Variable multiLetter;
    protected static Variable multiCase;
    protected static Variable alongname;
    protected static Product twox;
    protected static Product twoplusxtimesxplusy;
    protected static Product twotimesxtimesxtimesy;
    protected static Product nestedproductandsumtwoxy;
    protected static Product twoxysquared;
    protected static Product twoy;
    protected static Product fourxy;
    protected static Product xy;
    protected static Sum xplusy;
    protected static Sum xplustwo;
    protected static Sum xplusypluszplusone;
    protected static Expression xplusone;
    protected static Monomial xtimesx;
    protected static SimpleExpression xtimesxplusx;

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
        multiLetter = new Variable("multi");
        multiCase = new Variable("HeLlO");
        
        one = new Number(1);
        zero = new Number(0);
        two = new Number(02.0);
        four = new Number(4);
        twox = new Product(two, x);
        alongname = new Variable("alongname");
        twoplusxtimesxplusy = new Product(new Sum(two, x), new Sum(x, y));
        twotimesxtimesxtimesy = new Product(new Product(two, x), new Product(x, y));
        nestedproductandsumtwoxy = new Product(new Sum(two, new Product(x, y)), new Product(new Sum(two, y), x));
        xy = new Product(x,y);
        xplusy = new Sum(x,y);
        xplustwo = new Sum(x, two);
        xplusypluszplusone = new Sum(x, new Sum(y, new Sum(z, one)));
        xplusone = x.add(one);
        xtimesx = x.multiply(x);
        xtimesxplusx = new SimpleExpression(xtimesx, x);
        twoy = new Product(two, y);
        fourxy = new Product(new Product(x, y), four);
        twoxysquared = new Product(new Product(two, x), new Product(y, y));
        
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
