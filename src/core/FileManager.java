package core;

import ui.Menu;

import java.io.IOException;
import java.nio.file.*;

public class FileManager {
    private static String textFileRead;

    public static String getTextFileRead() {
        return textFileRead;
    }

    public void readLine(String path) throws IOException {
        Path file = Path.of(path);
        if (Files.exists(file)) {
           textFileRead = Files.readString(file);
        }
    }
    public void writeLine(String text, String path) throws IOException {
        Path writeFile = Path.of(path);
        Files.writeString(writeFile, text);
    }
}
