package Model;

import Controller.Validation;

public class Rectangle extends Shape{

    private double witdh, length;

    public Rectangle() {
    }

    public Rectangle(double witdh, double length) {
        this.witdh = witdh;
        this.length = length;
    }

    public double getWitdh() {
        return witdh;
    }

    public void setWitdh(double witdh) {
        if(witdh > 0){
           this.witdh = witdh;
        }else{
            throw new IllegalArgumentException("Greater than 0");
        }
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    
    
    @Override
    public double getPerimeter() {
        return 2*(length+witdh);
    }

    @Override
    public double getArea() {
        return length*witdh;
    }

    @Override
    public void printResult() {
        System.out.println("---- Rectangle ----");
        System.out.println("Width: " + witdh);
        System.out.println("Length: " + length);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

    @Override
    public void input() {
        try {
            double a = Validation.getFloat("Enter width: ");
            setWitdh(a);
            double b = Validation.getFloat("Enter length: ");
            setLength(b);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
