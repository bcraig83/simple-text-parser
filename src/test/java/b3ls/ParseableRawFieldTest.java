package b3ls;

import org.junit.Assert;
import org.junit.Test;

public class ParseableRawFieldTest {

  @Test
  public void shouldParseSimpleData() {

    RawField carrierCode = new RawField(2, "CarrierCode");
    ParseableRawField fixture = new ParseableRawField(carrierCode);

    String result = fixture.parse("AC1234");

    Assert.assertEquals("1234", result);
    Assert.assertEquals("AC", fixture.getContents());
    Assert.assertEquals("CarrierCode", fixture.getName());
  }
}