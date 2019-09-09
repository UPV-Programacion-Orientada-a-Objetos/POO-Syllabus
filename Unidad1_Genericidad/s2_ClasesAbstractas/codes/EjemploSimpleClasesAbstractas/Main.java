public class Main {

    public static void main(String[] args) {
        TwoDShape shapes[] = new TwoDShape[4];

        shapes[0] = new Triangle("outlined", 8.0, 12.0);
        shapes[1] = new Rectangle(10);
        shapes[2] = new Rectangle(10, 4);
        shapes[3] = new Triangle(7.0);

        for (int i=0; i < shapes.length; i++)
        {
            System.out.println("object is " + shapes[i].getName());
            System.out.println("Areas is " + shapes[i].area());
            System.out.println();
        }
    }
}
