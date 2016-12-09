package arrow;

public class Vector {

    public double x;
    public double y;

    public Vector setValue(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector setValue(Vector value) {
        this.x = value.x;
        this.y = value.y;
        return this;
    }
}
