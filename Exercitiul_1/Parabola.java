package Exercitiul_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Parabola {
    private int a, b, c;


    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public double[] varf() {
        double x = -b / (2.0 * a);
        double y = (-b * b + 4.0 * a * c) / (4.0 * a);
        return new double[]{x, y};
    }


    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }


    public static double[] mijloc(Parabola p1, Parabola p2) {
        double[] v1 = p1.varf();
        double[] v2 = p2.varf();
        double x = (v1[0] + v2[0]) / 2;
        double y = (v1[1] + v2[1]) / 2;
        return new double[]{x, y};
    }


    public static double distanta(Parabola p1, Parabola p2) {
        double[] v1 = p1.varf();
        double[] v2 = p2.varf();
        return Math.hypot(v2[0] - v1[0], v2[1] - v1[1]);
    }

}
