package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class RawGroup {

    private Map<String, ParseableRawField> groupMap = new LinkedHashMap<>();

    public void addRawFieldParser(ParseableRawField parser) {
        groupMap.put(parser.getName(), parser);
    }

    public Map<String, ParseableRawField> getMap() {
        return groupMap;
    }
}