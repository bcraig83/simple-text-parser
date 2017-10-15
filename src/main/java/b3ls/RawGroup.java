package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class RawGroup {

    private Map<String, RawGroupParser> groupMap = new LinkedHashMap<>();

    void addRawGroupParser(String name, RawGroupParser parser) {
        groupMap.put(name, parser);
    }
}
