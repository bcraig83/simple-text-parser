package b3ls;

public class ParseableRawField implements Parseable {

    private RawField rawField;

    public ParseableRawField(RawField rawField) {
        this.rawField = rawField;
    }

    @Override
    public String parse(String source) {
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
