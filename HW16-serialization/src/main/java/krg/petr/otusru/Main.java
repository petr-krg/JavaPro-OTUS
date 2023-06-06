package krg.petr.otusru;

import krg.petr.otusru.dataprocessor.*;
import krg.petr.otusru.model.Measurement;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String srcFileName = "data.json";
        String resFileName = "result.json";
        DataGenerator dataGenerator = new DataGenerator(srcFileName);
        dataGenerator.generateTestData(20);

        Loader loader = new ResourcesFileLoader(srcFileName);
        List<Measurement> measurements = loader.load();

        Processor processor = new ProcessorAggregator();
        Map<String, Double> processedData = processor.process(measurements);

        Serializer serializer = new FileSerializer(resFileName);
        serializer.serialize(processedData);

        System.out.println("45645456465");
    }
}