package core;
import java.io.IOException;
import java.nio.file.*;

public class FileManager {
    private String textFileRead;

    public String getTextFileRead() {
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
        if (Files.exists(writeFile)) {
            Files.writeString(writeFile, text);
        }
    }
}
