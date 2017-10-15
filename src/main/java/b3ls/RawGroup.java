package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class RawGroup {

    private String name;
    private Map<String, ParseableRawField> map = new LinkedHashMap<>();

    public RawGroup(String name) {
        this.name = name;
    }

    public void addRawFieldParser(ParseableRawField parser) {
        map.put(parser.getName(), parser);
    }

    public Map<String, ParseableRawField> getMap() {
        return map;
    }

    public String getName() {
        return name;
    }
}