package mindswap.academy.app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class HTMLUtil {

    public static String getHTML(){

        try {
           return Files.lines(Path.of("src/main/resources/index.html")).collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
