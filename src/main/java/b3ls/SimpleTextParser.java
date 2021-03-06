package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleTextParser implements Parseable {

  private Map<String, ParseableRawGroup> map = new LinkedHashMap<>();

  SimpleTextParser addRawGroupParser(ParseableRawGroup parser) {
    map.put(parser.getName(), parser);
    return this;
  }

  public String parse(String source) throws InvalidRawStringException {

    if (source.length() < this.getExpectedSize()) {
      throw new InvalidRawStringException();
    }

    String temp = source;

    for (ParseableRawGroup group : map.values()) {
      temp = group.parse(temp);
    }

    return temp;
  }

  String getItem(String groupName, String itemName) {
    String result = "";

    try {
      result = map.get(groupName).getValue(itemName);
    } catch (RawFieldDoesNotExistException e) {
      System.out.println("Raw field does not exist");
    }

    return result;
  }

  private int getExpectedSize() {
    int size = 0;

    for (ParseableRawGroup group : map.values()) {
      size += group.getSize();
    }

    return size;
  }
}
