package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.file.Files.readAllLines;


public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        String text = readAllLines(tome12Path, Charset.forName("windows-1251")).stream().collect(Collectors.joining(" ")) + " ";
        text += readAllLines(tome34Path, Charset.forName("windows-1251")).stream().collect(Collectors.joining(" "));

        Map<String, Integer> map = new HashMap<>();

        Stream.of(text.split("[^\\p{L}]+"))
                .map(String::toLowerCase)
                .filter(word -> word.length() >= 4)
                .forEach(word -> map.put(word, map.getOrDefault(word, 0) + 1));

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        entries.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()) == 0 ? e2.getKey().compareTo(e1.getKey()) : e1.getValue().compareTo(e2.getValue()));
        Collections.reverse(entries);

        return entries
                .stream()
                .filter(m -> (m.getValue() >= 10)).map(m -> m.getKey() + " - " + m.getValue())
                .collect(Collectors.joining("\n"));
    }

}