package utils;

import java.io.FileWriter;
import java.io.IOException;

public class SaveUtils {
    public String save(String content, String path, String format) throws IOException {
        try (FileWriter writer = new FileWriter(path + format)) {
            writer.write(content);
        }
        return path + format;
    }
}
