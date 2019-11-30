package com.piotrpiechota.guessthenumber2;

import java.util.Scanner;

public class GuessTheNumber2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 0;
        int max = 1000;

        System.out.println("Pomyśl liczbę od 1 do 1000, a ja ją zgadnę w max. 10 próbach. Gotowy?");

        // Potwierdzenie, gracz ma chwilę aby pomyśleć nad odpowiedzią
        String confirmation = scanner.nextLine();
        while (!"tak".equalsIgnoreCase(confirmation)) {
            System.out.println("Gotowy?");
            confirmation = scanner.nextLine();
        }

        // Zgadywanie
        guessTheNumber(scanner,min,max);
    }

    static void guessTheNumber(Scanner scanner, int min, int max) {
        int choice = -1;
        int limit = makeGuess(min, max);
        int cheatCount = 1;

        while (choice != 2) {
            if(cheatCount>10){
                System.out.println("Oszukujesz! Koniec gry!");
                return;
            }
            choice = getDigit(scanner);
            switch (choice) {
                case 0:
                    min = limit;
                    limit = makeGuess(min, max);
                    break;
                case 1:
                    max = limit;
                    limit = makeGuess(min, max);
                    break;
                case 2:
                    System.out.println("Ha! Wygrałem!");
                    break;
            }
            cheatCount++;
        }
    }

    static int makeGuess(int min, int max) {
        int guess = ((max - min) / 2) + min;
        System.out.println("Zgaduję: " + guess + "\n" +
                "Powiedz jak mi poszło [0 - za mało, 1 - za dużo, 2 - zgadłem]:");
        return guess;
    }

    private static int getDigit(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Podaj cyfrę: ");
        }
        int number = scanner.nextInt();
        while (number < 0 || number > 2) {
            System.out.print("Wybierz 1-3: ");
            number = getDigit(scanner);
        }
        return number;
    }
}
