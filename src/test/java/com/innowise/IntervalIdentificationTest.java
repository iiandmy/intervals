package com.innowise;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

public class IntervalIdentificationTest {
    private static Map<String[], String> intervalIdentificationTestCaseMap;

    @BeforeAll
    static void setUpTests() {
        intervalIdentificationTestCaseMap = new HashMap<>();

        intervalIdentificationTestCaseMap.put(new String[]{"C", "D"}, "M2");
        intervalIdentificationTestCaseMap.put(new String[]{"B", "F#", "asc"}, "P5");
        intervalIdentificationTestCaseMap.put(new String[]{"Fb", "Gbb"}, "m2");
        intervalIdentificationTestCaseMap.put(new String[]{"G", "F#", "asc"}, "M7");
        intervalIdentificationTestCaseMap.put(new String[]{"Bb", "A", "dsc"}, "m2");
        intervalIdentificationTestCaseMap.put(new String[]{"Cb", "Abb", "dsc"}, "M3");
        intervalIdentificationTestCaseMap.put(new String[]{"G#", "D#", "dsc"}, "P4");
        intervalIdentificationTestCaseMap.put(new String[]{"E", "B", "dsc"}, "P4");
        intervalIdentificationTestCaseMap.put(new String[]{"E#", "D#", "dsc"}, "M2");
        intervalIdentificationTestCaseMap.put(new String[]{"B", "G#", "dsc"}, "m3");
    }

    @Test
    void intervalIdentificationTest() {
        intervalIdentificationTestCaseMap.forEach((input, expectedOutput) ->
                Assertions.assertEquals(expectedOutput, Intervals.intervalIdentification(input)));
    }

}
