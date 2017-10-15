package b3ls;

public class ParseableRawGroup implements Parseable, RawGroup {

    private RawGroup rawGroup;

    public ParseableRawGroup(RawGroup rawGroup) {
        this.rawGroup = rawGroup;
    }

    @Override
    public String parse(String source) {
        return null;
    }

    @Override
    public void addRawGroupParser(String name, ParseableRawGroup parser) {
        rawGroup.addRawGroupParser(name, parser);
    }

    @Override
    public ParseableRawGroup getRawParserGroup(String name) {
        return rawGroup.getRawParserGroup(name);
    }
}
