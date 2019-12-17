package com.piotrpiechota.guessthenumber;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int toGuess = rand.nextInt(100) + 1;

        int guess = getNumber(scanner);
        while (guess != toGuess) {
            if (guess < toGuess) {
                System.out.println("Za mało!");
                guess = getNumber(scanner);
            } else {
                System.out.println("Za dużo!");
                guess = getNumber(scanner);
            }
        }
        System.out.println("Zgadłeś!");

        scanner.close();
    }

    private static int getNumber(Scanner scanner) {
        System.out.print("Zgadnij liczbę: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("To nie jest liczba.\nZgadnij liczbę: ");
        }
        return scanner.nextInt();
    }
}
