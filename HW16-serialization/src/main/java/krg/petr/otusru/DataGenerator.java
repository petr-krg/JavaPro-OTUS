package krg.petr.otusru;

import com.fasterxml.jackson.databind.ObjectMapper;
import krg.petr.otusru.model.Measurement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private final String fileName;

    public DataGenerator(String fileName) {
        this.fileName = fileName;
    }

    public void generateTestData(int count) {
        List<Measurement> measurements = generateMeasurements(count);
        saveToFile(measurements);
    }

    private List<Measurement> generateMeasurements(int count) {
        List<Measurement> measurements = new ArrayList<>();
        String[] names = {"Measurement1", "Measurement2", "Measurement3", "Measurement4", "Measurement5",
                          "Measurement6", "Measurement7", "Measurement8", "Measurement9", "Measurement10"};
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String name = names[random.nextInt(names.length)];
            double value = Math.round(random.nextDouble() * 10000) / 100.0;
            value = Math.min(value, 100.0);
            Measurement measurement = new Measurement(name, value);
            measurements.add(measurement);
        }
        return measurements;
    }

    private void saveToFile(List<Measurement> measurements) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), measurements);
            System.out.println("The file " + fileName + " was saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
