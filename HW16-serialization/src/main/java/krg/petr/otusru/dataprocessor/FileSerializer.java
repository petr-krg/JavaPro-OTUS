package krg.petr.otusru.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(data);
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(json);
            fileWriter.close();
            System.out.println("4564654654");
        } catch (IOException e) {
            throw new FileProcessException("1231231!!!");
        }
    }
}
