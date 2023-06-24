package krg.petr.otusru.dataprocessor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import krg.petr.otusru.model.Measurement;

import java.io.IOException;

public class MeasurementDeserializer extends JsonDeserializer<Measurement> {
    @Override
    public Measurement deserialize(JsonParser parser, DeserializationContext context) {
        try {
            JsonNode node = parser.getCodec().readTree(parser);
            String name = node.get("name").asText();
            double value = node.get("value").asDouble();

            return new Measurement(name, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
