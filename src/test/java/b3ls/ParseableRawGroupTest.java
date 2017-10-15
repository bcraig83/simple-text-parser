package b3ls;

import org.junit.Assert;
import org.junit.Test;

public class ParseableRawGroupTest {

    @Test
    public void shouldParseSimpleGroup() {

        RawGroup rawGroup = new RawGroup("Group1");
        RawField carrierCode = new RawField(2, "CarrierCode");
        RawField flightNumber = new RawField(4, "FlightNumber");

        ParseableRawField parseableCarrierCode = new ParseableRawField(carrierCode);
        ParseableRawField parseableFlightNumber = new ParseableRawField(flightNumber);

        rawGroup.addRawFieldParser(parseableCarrierCode);
        rawGroup.addRawFieldParser(parseableFlightNumber);

        ParseableRawGroup fixture = new ParseableRawGroup(rawGroup);

        String result = fixture.parse("AC1234");

        Assert.assertEquals("AC", fixture.getValue("CarrierCode"));
        Assert.assertEquals("1234", fixture.getValue("FlightNumber"));
    }
}