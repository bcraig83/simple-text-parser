package b3ls;

public class RawField {
    private int size;
    private String name;
    private String contents;

    // private String regexPattern; (will come eventually)
    // protected String errorMessage; (will be filled out if there are any problems)

    public RawField(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
