package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {

    // Validate integer input (menu choices, IDs, etc.)
    public static int parseInt(String input, String fieldName) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number.");
        }
    }

    // Validate non-empty string input
    public static String requireNonEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return input.trim();
    }

    // Validate positive number (IDs, duration)
    public static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than zero.");
        }
        return value;
    }

    // Validate yes/no input (optional utility)
    public static boolean parseYesNo(String input, String fieldName) {
        if (input.equalsIgnoreCase("yes")) return true;
        if (input.equalsIgnoreCase("no")) return false;

        throw new InvalidInputException(
                fieldName + " must be either 'yes' or 'no'."
        );
    }
}
