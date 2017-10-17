package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleTextParser implements Parseable {

    private Map<String, ParseableRawGroup> map = new LinkedHashMap<>();


    public void addRawGroupParser(ParseableRawGroup parser) {
        map.put(parser.getName(), parser);
    }


    public String parse(String source) {
        String temp = source;

        for (ParseableRawGroup group : map.values()) {
            temp = group.parse(temp);
        }

        return temp;
    }

    public String getItem(String groupName, String itemName) {
        return map.get(groupName).getValue(itemName);
    }
}
