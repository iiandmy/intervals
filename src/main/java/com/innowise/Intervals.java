package com.innowise;

import java.util.List;

public class Intervals {

    private static final String ILLEGAL_NUMBER_OF_ELEMENTS_MESSAGE = "Illegal number of elements in input array";
    private static final String CANNOT_IDENTIFY_INTERVAL_MESSAGE = "Cannot identify the interval";
    private static final String CANNOT_IDENTIFY_NOTE_MESSAGE = "Cannot identify the note";
    private static final int NUMBER_OF_ELEMENTS_WITHOUT_DESTINATION = 2;
    private static final int NUMBER_OF_ELEMENTS_WITH_DESTINATION = 3;
    private static final String NOTES_SEMITONES = "C--D--E-F--G--A--B-C";
    private static final List<String> ACCIDENTAL_LIST;
    private static final List<String> INTERVAL_LIST;
    private static final List<String> NATURAL_NOTE_LIST;

    static {
        ACCIDENTAL_LIST = List.of("bb", "b", "", "#", "##");
        INTERVAL_LIST = List.of("m2", "M2", "m3", "M3", "P4", "", "P5", "m6", "M6", "m7", "M7", "P8");
        NATURAL_NOTE_LIST = List.of("C", "D", "E", "F", "G", "A", "B");
    }

    public static String intervalConstruction(String[] args) {
        validateConstructionInput(args);

        String interval = args[0];
        String startingNote = args[1];

        int requestDegree = getRequestDegree(interval);
        String naturalNote = getNaturalNote(startingNote);

        String destinationNaturalNote = NATURAL_NOTE_LIST.get(
                (NATURAL_NOTE_LIST.indexOf(naturalNote) + requestDegree - 1) % NATURAL_NOTE_LIST.size()
        );
        int semitonesToDestination = getSemitoneNumberBetweenNotes(startingNote, destinationNaturalNote);
        int requestSemitoneNumber = INTERVAL_LIST.indexOf(interval) + 1;

        String requiredAccidental = ACCIDENTAL_LIST.get(
                ACCIDENTAL_LIST.indexOf("") + (requestSemitoneNumber - semitonesToDestination)
        );

        return destinationNaturalNote + requiredAccidental;
    }

    private static int getSemitoneNumberBetweenNotes(String fromNote, String toNote) {
        String fromNoteNatural = getNaturalNote(fromNote);
        String toNoteNatural = getNaturalNote(toNote);

        int fromNotePosition = NOTES_SEMITONES.indexOf(fromNoteNatural);
        int toNotePosition = NOTES_SEMITONES.indexOf(toNoteNatural);

        int semitonesFromAccidentals = getSemitonesFromAccidentals(fromNote) + getSemitonesFromAccidentals(toNote);

        return semitonesFromAccidentals + calculateSemitones(
                (fromNotePosition > toNotePosition) ?
                        NOTES_SEMITONES.replace(
                                NOTES_SEMITONES.substring(toNotePosition, fromNotePosition), ""
                        ) :
                        NOTES_SEMITONES.substring(fromNotePosition, toNotePosition)
        );
    }

    private static int getSemitonesFromAccidentals(String note) {
        String accidental = getNoteAccidental(note);
        return ACCIDENTAL_LIST.indexOf("") - ACCIDENTAL_LIST.indexOf(accidental);
    }

    private static int calculateSemitones(String input) {
        final char semitoneCharacter = '-';
        return (int) input.chars().filter(ch -> ch == semitoneCharacter).count();
    }

    private static String getNoteAccidental(String note) {
        return note.substring(1);
    }

    private static int getRequestDegree(String interval) {
        return Integer.parseInt(interval.substring(1));
    }

    private static String getNaturalNote(String note) {
        return note.substring(0, 1);
    }

    public static String intervalIdentification(String[] args) {
        return "123";
    }

    private static void validateConstructionInput(String[] args) {
        if (
                args.length > NUMBER_OF_ELEMENTS_WITH_DESTINATION ||
                args.length < NUMBER_OF_ELEMENTS_WITHOUT_DESTINATION
        ) {
            throw new RuntimeException(ILLEGAL_NUMBER_OF_ELEMENTS_MESSAGE);
        }
    }

    private static void validateIdentificationInput(String[] args) {

    }

    private static boolean isNoteCorrect(String note) {
        return true;
    }

    private static boolean isIntervalCorrect(String interval) {
        return true;
    }

    private static boolean isDirectionCorrect(String direction) {
        return true;
    }
}
