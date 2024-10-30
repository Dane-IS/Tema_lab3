package Exercitiul_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainApp_ex1 {
    public static void main(String[] args) {
        List<Parabola> parabola1 = new ArrayList<>();


        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Nr de parabole:");
            int n = scanner.nextInt();


            for (int i = 0; i < n; i++) {
                System.out.println("Adauga couficientii pentru parabola " + (i + 1) + ":");
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();


                Parabola parabola = new Parabola(a, b, c);
                parabola1.add(parabola);


                System.out.println(parabola);
                System.out.println("Varf: (" + parabola.varf()[0] + ", " + parabola.varf()[1] + ")");
            }


            if (parabola1.size() >= 2) {
                Parabola p1 = parabola1.get(0);
                Parabola p2 = parabola1.get(1);

                double[] mijloc = Parabola.mijloc(p1, p2);
                System.out.println("Mijlocul: (" + mijloc[0] + ", " + mijloc[1] + ")");

                double distanta = Parabola.distanta(p1, p2);
                System.out.println("Distanta: " + distanta);
            }
        }

    }
}
