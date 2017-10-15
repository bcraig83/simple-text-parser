package b3ls;

public class RawFieldParser extends RawField implements Parseable {

    private RawField rawField;

    public RawFieldParser(RawField rawField) {
        this.rawField = rawField;
    }

    @Override
    public String parser(String source) {
        int amountToConsume = rawField.getSize();
        rawField.setContents(source.substring(0, amountToConsume));
        return source.substring(amountToConsume, source.length());
    }

    public String getName() {
        return rawField.getName();
    }

    public String getContents() {
        return rawField.getContents();
    }
}
