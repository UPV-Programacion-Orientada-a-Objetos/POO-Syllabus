// A subclass of TwoDShape for rectangles.
public class Rectangle extends TwoDShape {
    // A default constructor.
    Rectangle()
    {
        super();
    }

    // Constructor for Rectangle.
    Rectangle(double w, double h)
    {
        super(w, h, "rectangle"); // call superclass constructor
    }

    // Construct a square.
    Rectangle(double x)
    {
        super(x, "rectangle");
    }

    // construct an object from an object.
    Rectangle (Rectangle ob)
    {
        super(ob); // pass object to TwoDShape constructor
    }

    boolean isSquare()
    {
        if (getWidth() == getHeight()) return true;
        return false;
    }

    double area()
    {
        return getWidth() * getHeight();
    }
}
