package com.piotrpiechota.lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Lotto {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Generuję liczby losowe
        Integer[] todayNumbers = new Integer[49];
        for (int i = 0; i < todayNumbers.length; i++) {
            todayNumbers[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(todayNumbers));

        // Konwertuję do tablicy intów prostych
        int[] generated = new int[6];
        for (int i = 0; i < generated.length; i++) {
            generated[i] = todayNumbers[i];
        }

        // Wczytuję liczby gracza
        int[] results = pickSixNumbers(scan);

        // Sprawdzenie - wyświetlenie
//        System.out.println(Arrays.toString(generated));
//        System.out.println(Arrays.toString(results));

        // Sprawdzenie i obliczenie wyników
        checkScore(results, generated);
    }

    static void checkScore(int[] picked, int[] generated) {
        int score = 0;
        for (int number : picked) {
            if (containsValue(generated, number)) {
                score++;
            }
        }

        switch (score) {
            case 6:
                System.out.println("Trafiłeś 6!");
                break;
            case 5:
                System.out.println("Trafiłeś 5!");
                break;
            case 4:
                System.out.println("Trafiłeś 4!");
                break;
            case 3:
                System.out.println("Trafiłeś 3!");
                break;
            default:
                System.out.println("Niestety, nie tym razem...");
                break;
        }
    }

    static int guessNumber(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("To nie liczba");
        }
        return scanner.nextInt();
    }

    static int[] pickSixNumbers(Scanner scanner) {
        int[] playerNumbers = new int[6];
        int index = 0;
        System.out.println("Podaj sześć liczb w zakresie 1-49: ");

        while (index < 6) {
            int pick = guessNumber(scanner);
            if (isInRange(pick)) {
                System.out.println("Liczba powinna być z zakresu 1-49");
            } else if (containsValue(playerNumbers, pick)) {
                System.out.println("Taka liczba została już podana");
            } else {
                playerNumbers[index] = pick;
                System.out.println("Liczba nr " + (index + 1) + ": " + playerNumbers[index]);
                index++;
            }
        }
        return playerNumbers;
    }

    static boolean containsValue(int[] tab, int number) {
        for (int value : tab) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    static boolean isInRange(int number) {
        return number < 1 || number > 49;
    }
}