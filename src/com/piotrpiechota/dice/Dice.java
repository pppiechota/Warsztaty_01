package com.piotrpiechota.dice;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dice {

    public static void main(String[] args) {

        int result = rollDice("2D10+3");
        System.out.println(result);
    }

    static int rollDice(String str) {
        // tworzę obiekt klasy Pattern, kompilując wyrażenie regularne
        Pattern pattern = Pattern.compile("(\\d)?D(\\d+)([+\\-]\\d+)?");
        // tworzę obiekt klasy Matcher, wywołując go na utworzonym Patternie i podając jako atrybut sprawdzany String
        Matcher matcher = pattern.matcher(str);
        int val = 0;
        int multi;      // ilość rzutów
        int sides;      // ilość ścianek na kostce
        int modifier;   // dodatkowy modyfikator

        if (matcher.find()) {
            multi = 1;
            modifier = 0;

            // Sprawdzam ilość rzutów
            if (matcher.group(1) != null) {
                multi = Integer.parseInt(matcher.group(1));
            }

            // Sprawdzam rodzaj kostki
            sides = Integer.parseInt(matcher.group(2));

            // Sprawdzam dodatkowy modyfikator
            if (matcher.group(3) != null) {
                modifier = Integer.parseInt(matcher.group(3));
            }

            // "Rzucam kośćmi" - losuję multi razy z przedziału sides
            for (int i = 0; i < multi; i++) {
                val += new Random().nextInt(sides) + 1;
            }

            // Dodaję modyfikator
            val += modifier;
        }
        return val;
    }
}