package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class RawGroup {

    private Map<String, ParseableRawGroup> groupMap = new LinkedHashMap<>();

    public void addRawGroupParser(String name, ParseableRawGroup parser) {
        groupMap.put(name, parser);
    }

    public Map<String, ParseableRawGroup> getMap() {
        return groupMap;
    }
}