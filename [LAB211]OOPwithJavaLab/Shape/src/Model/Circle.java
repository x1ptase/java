package Model;

import Controller.Validation;

public class Circle extends Shape {

    public double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("Greater than 0");
        }
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void printResult() {
        System.out.println("---- Circle ----");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

    @Override
    public void input() {
        try {
            double r = Validation.getFloat("Enter radius: ");
            setRadius(r);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
