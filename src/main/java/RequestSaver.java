import java.io.FileWriter;
import java.io.IOException;

public class RequestSaver {
    public String Save(String content, String path, String format) throws IOException {
        try (FileWriter writer = new FileWriter(path + format)) {
            writer.write(content);
        }
        return path + format;
    }
}
