package ru.job4j.condition;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* Class class for create triangle.
* @author atrifonov
* @since 04.07.2017
* @version 1
*/
public class Triangle {
	/**
	* Triangle's point a.
	*/
	private Point a;

	/**
	* Triangle's point b.
	*/
	private Point b;

	/**
	* Triangle's point c.
	*/
	private Point c;

	/**
	* Constructor - create new Triangle based on three point.
	* @param a Point a.
	* @param b Point b.
	* @param c Point c.
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
     * Return area triangle.
     * @return area.
     */
    public double area() {
        double area = 0.0;
        if (!((this.a.getX() == this.b.getX() && this.b.getX() == this.c.getX())
                || (this.a.getY() == this.b.getY() && this.b.getY() == this.c.getY()))) {
            double ab = lengthEdge(this.a, this.b);
            double ac = lengthEdge(this.a, this.c);
            double bc = lengthEdge(this.b, this.c);

            double p = (ab + bc + ac) / 2;
            area = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
			area = new BigDecimal(area).setScale(1, RoundingMode.HALF_UP).doubleValue();
        }

        return area;
    }

    /**
     * Return edge length of triangle.
     * @param a Point a.
     * @param b Point b.
     * @return value edge length of triangle.
     */
    public double lengthEdge(Point a, Point b) {
        return  Math.sqrt((b.getX() - a.getX()) * (b.getX() - a.getX()) + (b.getY() - a.getY()) * (b.getY() - a.getY()));
    }
}