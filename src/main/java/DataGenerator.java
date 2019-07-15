import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DataGenerator {
    static String data = "[{\n" +
            "    \"name\": \"University of Idaho\",\n" +
            "    \"num_students\": 11780,\n" +
            "    \"year_founded\": 1889\n" +
            "},{\n" +
            "    \"name\": \"Boise State University\",\n" +
            "    \"num_students\": 22113,\n" +
            "    \"year_founded\": 1932\n" +
            "},\n" +
            "{\n" +
            "    \"name\": \"Idaho State University\",\n" +
            "    \"num_students\": 12643,\n" +
            "    \"year_founded\": 1901\n" +
            "}]";

    static void generateJsonToFile(String fileName) throws IOException {
        deleteFile(fileName);
        File file = new File(fileName);
        Files.write(file.toPath(), data.getBytes());
    }

    static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
