package lombax5832.DarkSouls.util;

public class Vector {
	public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void normalize() {
        double d = length();
        x /= d;
        y /= d;
        z /= d;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}
