package krg.petr.otusru.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public ObjectForMessage copyField() {
        ObjectForMessage objectForMessage = new ObjectForMessage();
        objectForMessage.data = new ArrayList<>(data);
        return objectForMessage;
    }
}
