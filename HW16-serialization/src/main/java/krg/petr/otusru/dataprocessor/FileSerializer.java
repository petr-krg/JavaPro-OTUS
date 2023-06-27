package krg.petr.otusru.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final File fileJson;

    public FileSerializer(String fileName) {
        this.fileJson = new File(fileName);
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(fileJson, data);
        } catch (IOException e) {
            throw new FileProcessException("Error when serializing data to JSON: " + e.getMessage());
        }
    }
}
