package Model;

import Controller.Validation;

public class Triagle extends Shape {

    private double sideA, sideB, sideC;

    public Triagle() {
    }

    public Triagle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        if (sideA > 0) {
            this.sideA = sideA;
        } else {
            throw new IllegalArgumentException("Greater than 0");
        }
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        if (sideB > 0) {
            this.sideB = sideB;
        } else {
            throw new IllegalArgumentException("Greater than 0");
        }
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        if (sideC > 0) {
            this.sideC = sideC;
        } else {
            throw new IllegalArgumentException("Greater than 0");
        }
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double getArea() {
        double p = (sideA + sideB + sideC) / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public void printResult() {
        System.out.println("---- Triagle ----");
        System.out.println("Side A: " + sideA);
        System.out.println("Side B: " + sideB);
        System.out.println("Side C: " + sideC);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

    @Override
    public void input() {
        try {
            while (true) {
                double a = Validation.getFloat("Enter sideA: ");
                setSideA(a);
                double b = Validation.getFloat("Enter sideB: ");
                setSideB(b);
                double c = Validation.getFloat("Enter sideC: ");
                setSideC(c);
                if (!checkTriagle(a, b, c)) {
                    System.out.println("Triangle dont exist");
                } else {
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkTriagle(double a, double b, double c) {
        if (a + b > c && a + c > b && b + c > a) {
            return true;
        }
        return false;
    }

}
