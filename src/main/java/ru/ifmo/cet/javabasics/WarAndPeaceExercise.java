package ru.ifmo.cet.javabasics;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static String countWords(List<Path> textPaths) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (Path textPath: textPaths) {
            try (BufferedReader reader = Files.newBufferedReader(textPath, Charset.forName("windows-1251"))) {
                String line;
                while ((line = reader.readLine()) != null)
                    list.addAll(Arrays.asList(line.toLowerCase().split("[^\\p{L}]+")));
            } catch (IOException e) {
                System.out.println("Exception caught:");
                e.printStackTrace();
            }
        }

        for (String word : list) {
            if (map.containsKey(word)) map.put(word, map.get(word) + 1);
            else map.put(word, 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if (e1.getValue().compareTo(e2.getValue()) == 0) {
                    return e2.getKey().compareTo(e1.getKey());
                }
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        Collections.reverse(entries);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getKey().length() < 4 || entry.getValue() < 10) continue;
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

    /**
     * map lowercased words to its amount in text and concatenate its entries.
     * If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
     * Entries in final String should be also sorted by amount and then in alphabetical order if needed.
     * Also omit any word with lengths less than 4 and frequency less than 10
     * @return String
     */
    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        return countWords(Arrays.asList(tome12Path, tome34Path));
    }

}
