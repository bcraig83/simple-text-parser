package b3ls;

public class RawField {
    protected int size;
    private String name;
    private String contents;

    // private String regexPattern; (will come eventually)
    // protected String errorMessage; (will be filled out if there are any problems)

    public RawField() {
    }

    int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    void setContents(String contents) {
        this.contents = contents;
    }
}
