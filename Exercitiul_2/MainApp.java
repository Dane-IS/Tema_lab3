package Exercitiul_2;

import java.io.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

class Produs{
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate data_expirare;
    private static double incasari = 0.0;
    public Produs(String denumire, double pret, int cantitate, LocalDate data_expirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.data_expirare = data_expirare;
    }

    public String getDenumire() {
        return denumire;
    }
    public int getCantitate() {
        return cantitate;
    }
    public double getPret() {
        return pret;
    }
    public boolean esteExpirat() {
        return data_expirare.isBefore(LocalDate.now());
    }
    public static double getIncasari() {
        return incasari;
    }
    @Override
    public String toString() {
        return denumire + ", " + pret + ", " + cantitate + ", " + data_expirare;
    }

}
public class MainApp {
    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<Produs>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/Cursuri faculta/Teme lab PJ/Tema lab 3/src/Exercitiul_2/produse.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] valori = line.split(",");
                String denumire = valori[0].trim();
                double pret = Double.parseDouble(valori[1].trim());
                int cantitate = Integer.parseInt(valori[2].trim());
                LocalDate data_expirare = LocalDate.parse(valori[3].trim());

                produse.add(new Produs(denumire, pret, cantitate, data_expirare));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }


        Scanner scanner=new Scanner(System.in);
        int optiune;
        do {
            System.out.println("1.Afișarea tuturor produselor din colecția List ");
            System.out.println("2.Afișarea produselor expirate ");
            System.out.println("3.Vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc");
            System.out.println("4.Afișarea produselor cu prețul minim");
            System.out.println("5.salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură într-un fișier de ieșire.");
            System.out.println("Alegeti o optiune: ");
            optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    System.out.println("\nToate produsele:");
                    for (Produs produs : produse) {
                        System.out.println(produs);
                    }
                    break;
                case 2:
                    System.out.println("\nProdusele expirate:");
                    for (Produs produs : produse) {
                        if (produs.esteExpirat()) {
                            System.out.println(produs);
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nProdusele cu prețul minim:");
                    double pretMinim = produse.stream()
                            .min(Comparator.comparingDouble(Produs::getPret))
                            .map(Produs::getPret)
                            .orElse(Double.MAX_VALUE);

                    for (Produs produs : produse) {
                        if (produs.getPret() == pretMinim) {
                            System.out.println(produs);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Introduceți cantitatea minimă: ");
                    int cantitateMinima = scanner.nextInt();
                    System.out.print("Introduceți calea pentru fișierul de ieșire: ");
                    scanner.nextLine();
                    String filePath = scanner.nextLine();

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        for (Produs produs : produse) {
                            if (produs.getCantitate() < cantitateMinima) {
                                writer.write(produs.toString());
                                writer.newLine();
                            }
                        }
                        System.out.println("Produsele au fost salvate în fișier.");
                    } catch (IOException e) {
                        System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
                    }
                    break;
            }
        }while(optiune!=0);

        scanner.close();

        }
    }
