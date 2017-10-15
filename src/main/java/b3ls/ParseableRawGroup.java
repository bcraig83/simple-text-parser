package b3ls;

import java.util.Map;

public class ParseableRawGroup implements Parseable {

    private RawGroup rawGroup;

    public ParseableRawGroup(RawGroup rawGroup) {
        this.rawGroup = rawGroup;
    }

    @Override
    public String parse(String source) {
        Map<String, ParseableRawGroup> map = rawGroup.getMap();

        String temp = source;
        for (ParseableRawGroup group : map.values()) {
            temp = group.parse(temp);
        }

        return temp;
    }

    public Map<String, ParseableRawGroup> getMap() {
        return rawGroup.getMap();
    }
}
