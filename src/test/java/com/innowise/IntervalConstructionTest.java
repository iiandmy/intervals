package com.innowise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class IntervalConstructionTest {
    private static Map<String[], String> intervalConstructionTestCaseMap;

    @BeforeAll
    static void setUpTests() {
        intervalConstructionTestCaseMap = new HashMap<>();

        intervalConstructionTestCaseMap.put(new String[]{"M2", "C", "asc"}, "D");
        intervalConstructionTestCaseMap.put(new String[]{"P5", "B", "asc"}, "F#");
        intervalConstructionTestCaseMap.put(new String[]{"m2", "Bb", "dsc"}, "A");
        intervalConstructionTestCaseMap.put(new String[]{"M3", "Cb", "dsc"}, "Abb");
        intervalConstructionTestCaseMap.put(new String[]{"P4", "G#", "dsc"}, "D#");
        intervalConstructionTestCaseMap.put(new String[]{"m3", "B", "dsc"}, "G#");
        intervalConstructionTestCaseMap.put(new String[]{"m2", "Fb", "asc"}, "Gbb");
        intervalConstructionTestCaseMap.put(new String[]{"M2", "E#", "dsc"}, "D#");
        intervalConstructionTestCaseMap.put(new String[]{"P4", "E", "dsc"}, "B");
        intervalConstructionTestCaseMap.put(new String[]{"m2", "D#", "asc"}, "E");
        intervalConstructionTestCaseMap.put(new String[]{"M7", "G", "asc"}, "F#");
    }

    @Test
    void intervalConstructionTest() {
        intervalConstructionTestCaseMap.forEach((input, expectedOutput) ->
                Assertions.assertEquals(expectedOutput, Intervals.intervalConstruction(input)));
    }

}
