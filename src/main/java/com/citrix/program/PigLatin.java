package com.citrix.program;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/*
    @class PigLatin translates a string (word, sentence, or paragraph) into “pig-latin”
 */
public class PigLatin {
    /*
        main driving function to the program
     */
    public static void main(String[] args) {
        String inputSentence = "Hello apple stairway can’t end. this-thing Beach McCloud"; //default string

        System.out.println("Enter a word ,sentence or paragraph (separated by space)");
        Scanner sc = new Scanner(System.in);
        inputSentence = sc.nextLine();

        System.out.println("Before Translation");
        System.out.println(inputSentence);
        System.out.println("After Translation");
        System.out.println(transformInputString(inputSentence));
    }

    /*
     Objective of this function is to retain punctuation on the same relative place from the end of the word.
     @param originalWord is the original referance of the word
     @param wordToChange is word after applying various transformations
     @return String with replaced punctuations
    */
    public static String handlePunctuations(String originalWord, String wordToChange) {
        StringBuilder outputString = new StringBuilder(wordToChange.replaceAll("\\p{IsPunctuation}", ""));

        if (!(outputString.length() < wordToChange.length()))
            return wordToChange; // do not carry out string replace if there is no punctuation mark present

        int positionOfMark = 1;
        char mark = 0;
        for (int index = 0; index < originalWord.length(); index++) {
            if (isPunctuation(originalWord.charAt(index))) {
                positionOfMark += index;
                mark = originalWord.charAt(index);
            }
        }
        int endIndex = outputString.length() + (positionOfMark - originalWord.length());
        return outputString.substring(0, endIndex) + mark + outputString.substring(endIndex);
    }

    /*
         Objective of this function is to treat hyphens as two separate words
         @param wordToHandle is the input word which will be treated as two different words
         @return String after applying transformation and joining with hyphen
    */
    public static String handleHyphens(String wordToHandle) {
        StringJoiner listOfWords = new StringJoiner("-");
        if (wordToHandle.contains("-")) {
            Arrays.stream(wordToHandle.split("-"))
                    .forEach(element -> listOfWords.add(applyVowelCheck(element)));
        }
        return listOfWords.toString();
    }

    /*
         Objective of this function is check weather the word starts with vowel or consonant
         @param wordToCheck is the input word which will be checked for vowel
         @return original word appended with way if it starts with vowel or appended with ay if it starts with consonant
         or returned as it is if it contains 'way'
    */
    public static String applyVowelCheck(String wordToCheck) {
        return wordToCheck.endsWith("way") ? applyCapitalization(wordToCheck, wordToCheck) :
                String.valueOf(wordToCheck.charAt(0)).matches("[aeiouAEIOU]")
                        ? applyCapitalization(wordToCheck, wordToCheck + "way")
                        : applyCapitalization(wordToCheck, wordToCheck.substring(1) + wordToCheck.charAt(0) + "ay");
    }

    /*
         Objective of this function is to maintain capitalization of the output word
         @param originalWord is the input word which will be referred for uppercase characters
         @param wordToChange is the input word which will be changed by reference of param1
         @return String with appropriate capitalization applied
    */
    public static String applyCapitalization(String originalWord, String wordToChange) {
        StringBuilder outputString = new StringBuilder(wordToChange.toLowerCase());
        for (int index = 0; index < originalWord.length(); index++) {
            if (Character.isUpperCase(originalWord.charAt(index))) {
                outputString.setCharAt(index, Character.toUpperCase(outputString.charAt(index)));
            }
        }
        return handlePunctuations(originalWord, outputString.toString());
    }

    /*
        Utility function to check if the given character is a punctuation character
        @param character input character to check for punctuation
        @return boolean
    */
    public static boolean isPunctuation(char character) {
        return String.valueOf(character).matches("\\p{IsPunctuation}");
    }

    /*
         Utility function to transform given input string to apply various transformations
         @param inputString input String to apply transformations
         @return transformed String
    */
    public static String transformInputString(String inputString){
        StringJoiner outputSentence = new StringJoiner(" ");
        Arrays.stream(inputString.split(" ")).forEach(word -> {
            outputSentence.add((word.contains("-"))?handleHyphens(word):applyVowelCheck(word));
        });
        return outputSentence.toString();
    }
}
