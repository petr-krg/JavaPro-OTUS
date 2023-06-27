package krg.petr.otusru.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import krg.petr.otusru.model.Measurement;

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
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Measurement.class, new MeasurementDeserializer());
        objectMapper.registerModule(simpleModule);

        try (InputStream inputStream = getClass().getResourceAsStream("/" + fileName)) {
            if (inputStream == null) {
                throw new FileProcessException("File not found: " + fileName);
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<Measurement>>() {});
        } catch (IOException e) {
            throw new FileProcessException("Error processing file: " + fileName + "/n" + e.getMessage());
        }
    }
}
