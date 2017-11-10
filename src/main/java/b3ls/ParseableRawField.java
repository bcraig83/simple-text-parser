package b3ls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseableRawField implements Parseable {

  private RawField rawField;

  ParseableRawField(RawField rawField) {
    this.rawField = rawField;
  }

  @Override
  public String parse(String source) {
    int amountToConsume = rawField.getSize();
    String contents = source.substring(0, amountToConsume);

    if (!regexMatches(contents)) {
      rawField.setErrorMessage("Raw string "
          + contents + " does not match regex pattern "
          + rawField.getRegexPattern());
    } else {
      rawField.setContents(contents);
    }
    return source.substring(amountToConsume, source.length());
  }

  private boolean regexMatches(String contents) {
    String regexPattern = rawField.getRegexPattern();
    if (regexPattern == null || regexPattern.isEmpty()) {
      return true;
    }

    Pattern p = Pattern.compile(rawField.getRegexPattern());
    Matcher m = p.matcher(contents);
    return m.matches();
  }

  String getName() {
    return rawField.getName();
  }

  String getContents() {
    return rawField.getContents();
  }

  int getSize() {
    return rawField.getSize();
  }

  String getErrorMessage() {
    return rawField.getErrorMessage();
  }
}
