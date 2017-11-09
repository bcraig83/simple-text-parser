package b3ls;

import java.util.Map;

public class ParseableRawGroup implements Parseable {

    private RawGroup rawGroup;

    ParseableRawGroup(RawGroup rawGroup) {
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

    String getValue(String key) throws RawFieldDoesNotExistException {
        ParseableRawField rawField = rawGroup.getMap().get(key);

        if (rawField == null) {
            throw new RawFieldDoesNotExistException();
        }

        return rawField.getContents();
    }

    String getName() {
        return rawGroup.getName();
    }

    public int getSize() {
        return rawGroup.getSize();
    }
}
