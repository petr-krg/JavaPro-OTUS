package krg.petr.otusru.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import krg.petr.otusru.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(File.separator + fileName);
            List<Measurement> measurements = objectMapper.readValue(inputStream, new TypeReference<List<Measurement>>() {});

            if (inputStream != null) {
                inputStream.close();
            }

            return measurements;
        } catch (IOException e) {
            throw new FileProcessException("123123!!!");
        }
    }
}
