package com.innowise;

import java.util.List;

public class Intervals {

    private static final String ILLEGAL_NUMBER_OF_ELEMENTS_MESSAGE = "Illegal number of elements in input array";
    private static final String CANNOT_IDENTIFY_INTERVAL_MESSAGE = "Cannot identify the interval";
    private static final String CANNOT_IDENTIFY_NOTE_MESSAGE = "Cannot identify the note";
    private static final String CANNOT_IDENTIFY_DIRECTION = "Cannot identify the direction";
    private static final String ASCENDING_DIRECTION = "asc";
    private static final String DESCENDING_DIRECTION = "dsc";
    private static final int NUMBER_OF_ELEMENTS_WITHOUT_DIRECTION = 2;
    private static final int NUMBER_OF_ELEMENTS_WITH_DIRECTION = 3;
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
        String direction = args.length == NUMBER_OF_ELEMENTS_WITHOUT_DIRECTION ? ASCENDING_DIRECTION : args[2];

        int requestDegree = getRequestDegree(interval);
        String naturalNote = getNaturalNote(startingNote);

        String destinationNaturalNote = NATURAL_NOTE_LIST.get(
                direction.equals(ASCENDING_DIRECTION) ?
                        (NATURAL_NOTE_LIST.indexOf(naturalNote) + requestDegree - 1) % NATURAL_NOTE_LIST.size() :
                        (NATURAL_NOTE_LIST.size() + NATURAL_NOTE_LIST.indexOf(naturalNote) - requestDegree + 1) % NATURAL_NOTE_LIST.size()
        );
        int semitonesToDestination = direction.equals(ASCENDING_DIRECTION) ?
                getSemitoneNumberBetweenNotes(startingNote, destinationNaturalNote, direction) :
                getSemitoneNumberBetweenNotes(destinationNaturalNote, startingNote, direction);

        int requestSemitoneNumber = INTERVAL_LIST.indexOf(interval) + 1;

        String requiredAccidental = ACCIDENTAL_LIST.get(
                direction.equals(ASCENDING_DIRECTION) ?
                        ACCIDENTAL_LIST.indexOf("") + (requestSemitoneNumber - semitonesToDestination) :
                        ACCIDENTAL_LIST.indexOf("") - (requestSemitoneNumber - semitonesToDestination)
        );

        return destinationNaturalNote + requiredAccidental;
    }

    public static String intervalIdentification(String[] args) {
        validateIdentificationInput(args);

        return "123";
    }

    private static int getSemitoneNumberBetweenNotes(String fromNote, String toNote, String direction) {
        String fromNoteNatural = getNaturalNote(fromNote);
        String toNoteNatural = getNaturalNote(toNote);

        int fromNotePosition = NOTES_SEMITONES.indexOf(fromNoteNatural);
        int toNotePosition = NOTES_SEMITONES.indexOf(toNoteNatural);

        int semitonesFromAccidentals = getSemitonesFromAccidentals(fromNote) + getSemitonesFromAccidentals(toNote);

        if (direction.equals(DESCENDING_DIRECTION)) semitonesFromAccidentals *= -1;

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

    private static void validateConstructionInput(String[] args) {
        checkForArgsLength(args);

        if (!isNoteCorrect(args[1])) {
            throw new RuntimeException(CANNOT_IDENTIFY_NOTE_MESSAGE);
        }
        if (!isIntervalCorrect(args[0])) {
            throw new RuntimeException(CANNOT_IDENTIFY_INTERVAL_MESSAGE);
        }
        if (args.length == NUMBER_OF_ELEMENTS_WITH_DIRECTION && !isDirectionCorrect(args[2])) {
            throw new RuntimeException(CANNOT_IDENTIFY_DIRECTION);
        }
    }

    private static void validateIdentificationInput(String[] args) {
        checkForArgsLength(args);

        if (!isNoteCorrect(args[0]) && !isNoteCorrect(args[1])) {
            throw new RuntimeException(CANNOT_IDENTIFY_NOTE_MESSAGE);
        }
        if (args.length == NUMBER_OF_ELEMENTS_WITH_DIRECTION && !isDirectionCorrect(args[2])) {
            throw new RuntimeException(CANNOT_IDENTIFY_DIRECTION);
        }
    }

    private static void checkForArgsLength(String[] args) {
        if (
                args.length > NUMBER_OF_ELEMENTS_WITH_DIRECTION ||
                args.length < NUMBER_OF_ELEMENTS_WITHOUT_DIRECTION
        ) {
            throw new RuntimeException(ILLEGAL_NUMBER_OF_ELEMENTS_MESSAGE);
        }
    }

    private static boolean isNoteCorrect(String note) {
        if (note.isBlank()) return false;

        String naturalNote = getNaturalNote(note);
        String accidentals = getNoteAccidental(note);

        return NATURAL_NOTE_LIST.contains(naturalNote) && ACCIDENTAL_LIST.contains(accidentals);
    }

    private static boolean isIntervalCorrect(String interval) {
        return !interval.isBlank() && INTERVAL_LIST.contains(interval);
    }

    private static boolean isDirectionCorrect(String direction) {
        return direction.equals(ASCENDING_DIRECTION) || direction.equals(DESCENDING_DIRECTION);
    }
}
