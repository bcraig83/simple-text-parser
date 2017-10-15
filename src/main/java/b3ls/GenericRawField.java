package b3ls;

public class GenericRawField implements RawField {
    protected int size;
    private String name;
    private String contents;

    // private String regexPattern; (will come eventually)
    // protected String errorMessage; (will be filled out if there are any problems)

    int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getContents() {
        return contents;
    }

    void setContents(String contents) {
        this.contents = contents;
    }
}
