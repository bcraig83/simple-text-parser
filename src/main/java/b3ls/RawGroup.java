package b3ls;

public interface RawGroup {

    public void addRawGroupParser(String name, ParseableRawGroup parser);

    public ParseableRawGroup getRawParserGroup(String name);
}
