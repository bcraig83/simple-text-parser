package b3ls;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

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

  // #4 and #5
  @Test
  public void shouldReportErrorWhenRegexDoesNotMatch() {
    ParseableRawField fixture = new ParseableRawField
        (new RawField(2, "CarrierCode")
            .setRegexPattern("[A-Z]{2}"));

    String result = fixture.parse("1234");

    assertThat(result, is(equalTo("34")));
    assertThat(fixture.getContents(), is(equalTo(null)));
    assertThat(fixture.getErrorMessage(),
        is(equalTo("Raw string [12] does not match regex pattern [[A-Z]{2}]")));
  }
}