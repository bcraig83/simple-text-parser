package b3ls;

public class ParseableRawField implements Parseable, RawField {

    private RawField rawField;

    public ParseableRawField(RawField rawField) {
        this.rawField = rawField;
    }

    @Override
    public String parse(String source) {
        return null;
    }

    @Override
    public void setSize(int size) {
        rawField.setSize(size);
    }

    @Override
    public String getName() {
        return rawField.getName();
    }

    @Override
    public void setName(String name) {
        rawField.setName(name);
    }

    @Override
    public String getContents() {
        return rawField.getContents();
    }
}
