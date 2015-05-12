package expresso;

public class TestSetup {
    
    protected static Number one;
    protected static Number zero;
    protected static Number two;
    protected static Variable x;
    protected static Variable y;
    protected static Product xy;
    protected static Sum xplusy;
    protected static Expression xplusytimesxy;
    protected static Expression xytimesxplusy;
    protected static Expression xplusyplusxy;
    protected static Expression xyplusxplusy;
    protected static Expression dxy;
    protected static Expression dxplusy;
    protected static Expression oneplusone ;
    protected static Expression onetimesone;
    
    public static void setup(){
        x = new Variable("x");
        y = new Variable("y");
        one = new Number(1);
        zero = new Number(0);
        two = new Number(2);
        x = new Variable("x");
        y = new Variable("y");
        xy = new Product(x,y);
        xplusy = new Sum(x,y);
        xplusytimesxy = new Product(xplusy, xy); 
       // xplusytimesxy = Expression.parse("(x+y)*x*y"); 
        xytimesxplusy = new Product(xy, xplusy);
        //xytimesxplusy = Expression.parse("x*y*(x+y)");
        xplusyplusxy = new Sum(xplusy, xy); 
        //xplusyplusxy = Expression.parse("x+y+x*y");
        xyplusxplusy = new Sum(xy, xplusy);
        //xyplusxplusy = Expression.parse("x*y+x+y");
        dxy = xy.differentiate(x);
        dxplusy = xplusy.differentiate(x);
        
        oneplusone = Expression.parse("1+1");
        onetimesone = Expression.parse("1*1");
    }
}
