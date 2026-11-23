package Utilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readJsonFile(String fileName) {
        try (InputStream inputStream =
                     JsonDataReader.class.getClassLoader()
                             .getResourceAsStream(fileName)) {

            return mapper.readTree(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getTestData(String fileName, String parentKey, String key) {
        JsonNode jsonNode = readJsonFile(fileName);

        return jsonNode.get(parentKey).get(key).asText();
    }
}
