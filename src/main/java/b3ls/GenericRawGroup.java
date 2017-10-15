package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenericRawGroup implements RawGroup {

    private Map<String, ParseableRawGroup> groupMap = new LinkedHashMap<>();

    public void addRawGroupParser(String name, ParseableRawGroup parser) {
        groupMap.put(name, parser);
    }

    @Override
    public Map<String, ParseableRawGroup> getMap() {
        return groupMap;
    }
}