package com.piotrpiechota.popularwords;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PopularWords {

    public static void main(String[] args) {
        String[] dictionary = {"oraz", "ostatnie", "ponieważ", "także", "które", "czyli",
                "który", "która", "również", "będę", "jest", "inne", "masz", "którą", "takie"};
        String url = "http://www.onet.pl/";
        Path keywords = Paths.get("popular_words.txt");
        Path filteredKeywords = Paths.get("filtered_popular_words.txt");

        String headlines = querySite(url);

        saveKeywords(keywords, headlines);

        filterKeywords(keywords, filteredKeywords, dictionary);
    }

    static void filterKeywords(Path source, Path target, String[] dictionary) {
        List<String> filtered = new ArrayList<>();

        try {
            Scanner scan = new Scanner(source);
            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                if (!Arrays.toString(dictionary).contains(word.toLowerCase())) { // Trochę karkołomne, ale w takie rejony dotarłem...
                    filtered.add(word);
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("Unable to load the file.");
        }
        System.out.println("Saving file to " + target.toString());
        writeFile(target, filtered);
    }

    static void saveKeywords(Path target, String text) {
        String[] split = text.split(" ");
        List<String> properWords = new ArrayList<>();

        for (String str : split) {
            if (str.length() > 3) {
                properWords.add(str);
            }
        }
        System.out.println("Saving file to " + target.toString());
        writeFile(target, properWords);
    }

    static String querySite(String url) {
        Connection connect = Jsoup.connect(url);
        StringBuilder sb = new StringBuilder();

        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                sb.append(elem.text()).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Unable to make connection.");
        }
        return sb.toString().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", " ");
    }

    static Path writeFile(Path name, List<String> content) {
        try {
            Files.write(name, content);
        } catch (IOException e) {
            System.out.println("Unable to create a file in the location.");
        }
        return name;
    }
}
