package pairmatching.data.crew;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.Application;

public class ReadResourceFile {
    public static List<String> readResourceFile(String fileName) {
        InputStream resourceAsStream = Application.class.getClassLoader().getResourceAsStream(fileName);
        if (resourceAsStream == null) {
            throw new RuntimeException("Resource not found: " + fileName);
        }
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
        return bufferedReader.lines().collect(Collectors.toList());
    }
}
