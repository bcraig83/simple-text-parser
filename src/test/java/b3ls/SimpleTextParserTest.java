package b3ls;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTextParserTest {

    @Test
    public void exampleOne() {
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

}