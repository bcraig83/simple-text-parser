package b3ls;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTextParserTest {

  @Test
  public void exampleOne_flightInformation() throws InvalidRawStringException {
    // Assume the string is "AC1213FRAYUL"
    // This can be defined as:
    //      - Carrier       [2 characters]
    //      - Flight Number [4 characters]
    //      - Departure     [3 characters]
    //      - Arrival       [3 characters]

    // Note; typically, setup like this would be hidden in a factory class.
    RawGroup rawGroup = new RawGroup("FlightInfo");

    rawGroup.addRawFieldParser(new ParseableRawField(new RawField(2, "Carrier")));
    rawGroup.addRawFieldParser(new ParseableRawField(new RawField(4, "FlightNumber")));
    rawGroup.addRawFieldParser(new ParseableRawField(new RawField(3, "Departure")));
    rawGroup.addRawFieldParser(new ParseableRawField(new RawField(3, "Arrival")));

    SimpleTextParser parser = new SimpleTextParser();
    parser.addRawGroupParser(new ParseableRawGroup(rawGroup));

    String result = parser.parse("AC1213FRAYUL");
    Assert.assertEquals("", result);

    String carrier = parser.getItem("FlightInfo", "Carrier");
    Assert.assertEquals("AC", carrier);

    String flightNumber = parser.getItem("FlightInfo", "FlightNumber");
    Assert.assertEquals("1213", flightNumber);

    String departure = parser.getItem("FlightInfo", "Departure");
    Assert.assertEquals("FRA", departure);

    String arrival = parser.getItem("FlightInfo", "Arrival");
    Assert.assertEquals("YUL", arrival);
  }

  @Test
  public void exampleTwo_addressInformation() throws InvalidRawStringException {
    // Assume the string is "CRAIG BEN   086-1234567 Made-Up Road   Somewhere   Austria   086-7654321 A.N.Other Rd.  Nowhere     Austria   "
    // This can be defined as:
    //      - Name
    //          - Surname       [6 chars]
    //          - First name    [6 chars]
    //      - Home Address
    //          - Phone number  [12 chars]
    //          - Street        [15 chars]
    //          - Town          [12 chars]
    //          - Country       [10 chars]
    //      - Work Address
    //          - Phone number  [12 chars]
    //          - Street        [15 chars]
    //          - Town          [12 chars]
    //          - Country       [10 chars]

    // Note; typically, setup like this would be hidden in a factory class.
    SimpleTextParser parser = new SimpleTextParser()
        .addRawGroupParser(new ParseableRawGroup(new RawGroup("Name")
            .addRawFieldParser(new ParseableRawField(new RawField(6, "Surname")))
            .addRawFieldParser(new ParseableRawField(new RawField(6, "Given")))))
        .addRawGroupParser(new ParseableRawGroup(new RawGroup("HomeAddress")
            .addRawFieldParser(new ParseableRawField(new RawField(12, "PhoneNumber")))
            .addRawFieldParser(new ParseableRawField(new RawField(15, "Street")))
            .addRawFieldParser(new ParseableRawField(new RawField(12, "Town")))
            .addRawFieldParser(new ParseableRawField(new RawField(10, "Country")))))
        .addRawGroupParser(new ParseableRawGroup(new RawGroup("WorkAddress")
            .addRawFieldParser(new ParseableRawField(new RawField(12, "PhoneNumber")))
            .addRawFieldParser(new ParseableRawField(new RawField(15, "Street")))
            .addRawFieldParser(new ParseableRawField(new RawField(12, "Town")))
            .addRawFieldParser(new ParseableRawField(new RawField(10, "Country")))));

    String result = parser.parse(
        "CRAIG BEN   086-1234567 Made-Up Road   Somewhere   Austria   086-7654321 A.N.Other Rd.  Nowhere     Austria   ");
    Assert.assertEquals("", result);

    String surname = parser.getItem("Name", "Surname");
    Assert.assertEquals("CRAIG ", surname);

    String given = parser.getItem("Name", "Given");
    Assert.assertEquals("BEN   ", given);

    String homePhoneNumber = parser.getItem("HomeAddress", "PhoneNumber");
    Assert.assertEquals("086-1234567 ", homePhoneNumber);

    String homeStreet = parser.getItem("HomeAddress", "Street");
    Assert.assertEquals("Made-Up Road   ", homeStreet);

    String homeTown = parser.getItem("HomeAddress", "Town");
    Assert.assertEquals("Somewhere   ", homeTown);

    String homeCountry = parser.getItem("HomeAddress", "Country");
    Assert.assertEquals("Austria   ", homeCountry);

    String workPhoneNumber = parser.getItem("WorkAddress", "PhoneNumber");
    Assert.assertEquals("086-7654321 ", workPhoneNumber);

    String workStreet = parser.getItem("WorkAddress", "Street");
    Assert.assertEquals("A.N.Other Rd.  ", workStreet);

    String workTown = parser.getItem("WorkAddress", "Town");
    Assert.assertEquals("Nowhere     ", workTown);

    String workCountry = parser.getItem("WorkAddress", "Country");
    Assert.assertEquals("Austria   ", workCountry);

  }

  @Test
  public void shouldHandleCallToGetNonExistingField() throws InvalidRawStringException {
    RawGroup rawGroup = new RawGroup("FlightInfo");
    rawGroup.addRawFieldParser(
        new ParseableRawField(
            new RawField(2, "AirlineDesignatorOfBoardingPassIssuer")));

    SimpleTextParser parser = new SimpleTextParser();
    parser.addRawGroupParser(new ParseableRawGroup(rawGroup));

    String result = parser.parse("AC1213FRAYUL");
    Assert.assertEquals("1213FRAYUL", result);

    String resultString = parser.getItem("FlightInfo", "AirlineDesignatorOfBoardingPass");

    Assert.assertEquals("", resultString);
  }

  @Test(expected = InvalidRawStringException.class)
  public void shouldNotAllowTextStringThatIsSmallerThanExpected() throws InvalidRawStringException {
    SimpleTextParser parser = new SimpleTextParser()
        .addRawGroupParser(new ParseableRawGroup(new RawGroup("Customer")
            .addRawFieldParser(new ParseableRawField(new RawField(10, "Given")))
            .addRawFieldParser(new ParseableRawField(new RawField(10, "Surname")))));

    String result = parser.parse("LILY BREID");
  }
}