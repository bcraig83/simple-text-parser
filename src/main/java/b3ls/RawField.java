package b3ls;

public class RawField {

  private String regexPattern;
  private String errorMessage;
  private int size;
  private String name;
  private String contents;

  RawField(int size, String name) {
    this.size = size;
    this.name = name;
  }

  int getSize() {
    return size;
  }

  String getName() {
    return name;
  }

  String getContents() {
    return contents;
  }

  void setContents(String contents) {
    this.contents = contents;
  }

  String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getRegexPattern() {
    return regexPattern;
  }

  public void setRegexPattern(String regexPattern) {
    this.regexPattern = regexPattern;
  }
}
