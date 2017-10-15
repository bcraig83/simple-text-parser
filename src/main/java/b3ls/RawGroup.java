package b3ls;

import java.util.Map;

public interface RawGroup {

    public void addRawGroupParser(String name, ParseableRawGroup parser);

    public Map<String, ParseableRawGroup> getMap();

}
