package labos_04;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
       return y;
    }

    public Point translate(Point dp) {
        return new Point(dp.x+x,dp.y+y);
        // vraća NOVU točku translatiranu za argument tj. THIS+DP...
    }

    public Point difference(Point p) {
        // vraća NOVU točku koja predstavlja razliku THIS-P...
        return new Point(x-p.x,y-p.y);
    }

    @Override
    public String toString() {
        return x +" "+ y ;
    }
}
