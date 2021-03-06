package b3ls;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class RawGroup {

  private String name;
  private Map<String, ParseableRawField> map = new LinkedHashMap<>();

  RawGroup(String name) {
    this.name = name;
  }

  RawGroup addRawFieldParser(ParseableRawField parser) {
    map.put(parser.getName(), parser);
    return this;
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

  Map<String, String> getErrors() {

    Map<String, String> results = new HashMap<>();

    for (ParseableRawField field : map.values()) {
      if (field.getErrorMessage() != null) {
        results.put(field.getName(), field.getErrorMessage());
      }
    }

    return results;
  }
}