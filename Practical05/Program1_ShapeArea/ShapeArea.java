import java.util.Scanner;

abstract class Shape {
    abstract double area();
}
class Rectangle extends Shape {
    private final double length, width;
    Rectangle(double length, double width) { this.length = length; this.width = width; }
    @Override double area() { return length * width; }
}
class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}
class Triangle extends Shape {
    private final double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }
    @Override double area() { return 0.5 * base * height; }
}

public class ShapeArea {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter triangle base and height: ");
            Shape triangle = new Triangle(sc.nextDouble(), sc.nextDouble());
            System.out.println("Area of triangle: " + triangle.area());
            System.out.print("Enter circle radius: ");
            Shape circle = new Circle(sc.nextDouble());
            System.out.println("Area of circle: " + circle.area());
            System.out.print("Enter rectangle length and width: ");
            Shape rectangle = new Rectangle(sc.nextDouble(), sc.nextDouble());
            System.out.println("Area of rectangle: " + rectangle.area());
        }
    }
}
