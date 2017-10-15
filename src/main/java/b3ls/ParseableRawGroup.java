package b3ls;

import java.util.Map;

public class ParseableRawGroup implements Parseable {

    private RawGroup rawGroup;

    public ParseableRawGroup(RawGroup rawGroup) {
        this.rawGroup = rawGroup;
    }

    @Override
    public String parse(String source) {
        Map<String, ParseableRawField> map = rawGroup.getMap();

        String temp = source;
        for (ParseableRawField group : map.values()) {
            temp = group.parse(temp);
        }

        return temp;
    }

    public String getValue(String key) {
        return rawGroup.getMap().get(key).getContents();
    }

    public String getName() {
        return rawGroup.getName();
    }
}
