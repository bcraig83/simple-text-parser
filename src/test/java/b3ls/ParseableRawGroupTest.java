package b3ls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Map;
import org.junit.Test;

public class ParseableRawGroupTest {

  @Test
  public void shouldParseSimpleGroup() throws RawFieldDoesNotExistException {

    RawGroup rawGroup = new RawGroup("Group1");
    RawField carrierCode = new RawField(2, "CarrierCode");
    RawField flightNumber = new RawField(4, "FlightNumber");

    ParseableRawField parseableCarrierCode = new ParseableRawField(carrierCode);
    ParseableRawField parseableFlightNumber = new ParseableRawField(flightNumber);

    rawGroup.addRawFieldParser(parseableCarrierCode);
    rawGroup.addRawFieldParser(parseableFlightNumber);

    ParseableRawGroup fixture = new ParseableRawGroup(rawGroup);

    String result = fixture.parse("AC1234");

    assertThat(fixture.getValue("CarrierCode"), is(equalTo("AC")));
    assertThat(fixture.getValue("FlightNumber"), is(equalTo("1234")));
    assertThat(result, is(""));
    assertThat(fixture.getSize(), is(6));
  }

  // #5
  @Test
  public void shouldReturnNoErrorsWhenStringIsSuccessfullyParsed() {
    try {
      ParseableRawGroup fixture =
          new ParseableRawGroup(new RawGroup("Group2")
              .addRawFieldParser(new ParseableRawField(new RawField(5, "FiveDigits")))
              .addRawFieldParser(new ParseableRawField(new RawField(3, "ThreeChars"))));

      String result = fixture.parse("12345ABC");
      String firstField = fixture.getValue("FiveDigits");
      String secondField = fixture.getValue("ThreeChars");

      assertThat(result, is(equalTo("")));
      assertThat(firstField, is(equalTo("12345")));
      assertThat(secondField, is(equalTo("ABC")));

      Map<String, String> errors = fixture.getErrors();
      assertNotNull(errors);
      assertThat(errors.size(), is(equalTo(0)));
    } catch (RawFieldDoesNotExistException e) {
      fail("Unexpected exception thrown");
    }
  }

  // #4
  @Test
  public void shouldReturnAnErrorWhenRegexDoesNotMatch() {
    try {
      ParseableRawGroup fixture =
          new ParseableRawGroup(
              new RawGroup("Group2")
                  .addRawFieldParser(
                      new ParseableRawField(
                          new RawField(5, "FiveDigits")
                              .setRegexPattern("[0-9]{5}")))
                  .addRawFieldParser(
                      new ParseableRawField(
                          new RawField(3, "ThreeChars")
                              .setRegexPattern("[A-Z]{3}"))));

      String result = fixture.parse("1A3F5!@#");
      String firstField = fixture.getValue("FiveDigits");
      String secondField = fixture.getValue("ThreeChars");

      assertThat(result, is(equalTo("")));
      assertThat(firstField, is(equalTo(null)));
      assertThat(secondField, is(equalTo(null)));

      Map<String, String> errors = fixture.getErrors();
      assertNotNull(errors);
      assertThat(errors.size(), is(equalTo(2)));
      assertThat(errors.get("FiveDigits"),
          is(equalTo("Raw string 1A3F5 does not match regex pattern [0-9]{5}")));
      assertThat(errors.get("ThreeChars"),
          is(equalTo("Raw string !@# does not match regex pattern [A-Z]{3}")));

    } catch (RawFieldDoesNotExistException e) {
      fail("Unexpected exception thrown");
    }
  }

  // TODO: Need to add a test where we try and retrieve something that does not exist.
}