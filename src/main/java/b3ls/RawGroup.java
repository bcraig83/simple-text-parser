package b3ls;

import java.util.LinkedHashMap;
import java.util.Map;

public class RawGroup {

  private String name;
  private Map<String, ParseableRawField> map = new LinkedHashMap<>();

  RawGroup(String name) {
    this.name = name;
  }

  void addRawFieldParser(ParseableRawField parser) {
    map.put(parser.getName(), parser);
  }

  Map<String, ParseableRawField> getMap() {
    return map;
  }

  String getName() {
    return name;
  }

  int getSize() {

    int size = 0;

    for (ParseableRawField field : map.values()) {
      size += field.getSize();
    }

    return size;
  }

  public Map<String, String> getErrors() {
    return null;
  }
}