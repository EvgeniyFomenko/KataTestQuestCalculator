package ru.efomenko;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    final static String[] romanNumb = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    final static String[] romanHighNumb = {"L", "C"};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws IOException {
        input = input.toUpperCase().trim();
        String result = " ";
        boolean isRomanMembers = false;

        String[] expressionMembers = input.split(" ");

        int numberOfOperands = 0;
        for (String s : expressionMembers) {
            if (s.equals("+") || s.equals("*") || s.equals("/") || s.equals("-")) {
                numberOfOperands++;
            }
        }
        if (numberOfOperands > 1 || numberOfOperands == 0 || expressionMembers.length <= 2) {
            throw new IOException("вырожение составлено не верно");
        }

        for (String s : romanNumb) {
            if (expressionMembers[0].matches(s) && isArabicNumbers(expressionMembers[2]) || isArabicNumbers(expressionMembers[0]) && expressionMembers[2].matches(s)) {
                throw new IOException("Использованно не верное сочетание символов");
            }
        }
        for (int i = 0; i < romanNumb.length; i++) {
            if (expressionMembers[0].matches(romanNumb[i]) || expressionMembers[2].matches(romanNumb[i])) {
                if (expressionMembers[0].matches(romanNumb[i])) {
                    expressionMembers[0] = String.valueOf(i + 1);
                }
                if (expressionMembers[2].matches(romanNumb[i])) {
                    expressionMembers[2] = String.valueOf(i + 1);
                }
                isRomanMembers = true;
            }
        }
        checkOptimalNumber(Integer.parseInt(expressionMembers[0]));
        checkOptimalNumber(Integer.parseInt(expressionMembers[2]));
        if (!isRomanMembers) {
            switch (expressionMembers[1]) {
                case "+" -> {
                    return String.valueOf(Integer.parseInt(expressionMembers[0]) + Integer.parseInt(expressionMembers[2]));
                }
                case "-" -> {
                    return String.valueOf(Integer.parseInt(expressionMembers[0]) - Integer.parseInt(expressionMembers[2]));
                }
                case "*" -> {
                    return String.valueOf(Integer.parseInt(expressionMembers[0]) * Integer.parseInt(expressionMembers[2]));
                }
                case "/" -> {
                    if (Integer.parseInt(expressionMembers[2]) == 0) {
                        throw new ArithmeticException("Деление на ноль не возможно");
                    }
                    return String.valueOf(Integer.parseInt(expressionMembers[0]) / Integer.parseInt(expressionMembers[2]));
                }
            }
        } else {
            switch (expressionMembers[1]) {
                case "+" -> {
                    return convertArabicToRoman(Integer.parseInt(expressionMembers[0]) + Integer.parseInt(expressionMembers[2]));
                }
                case "-" -> {
                    if (Integer.parseInt(expressionMembers[0]) < Integer.parseInt(expressionMembers[2])) {
                        throw new IOException("При вычислении будет получено отрецательное значение");
                    }
                    return convertArabicToRoman(Integer.parseInt(expressionMembers[0]) - Integer.parseInt(expressionMembers[2]));
                }
                case "*" -> {
                    return convertArabicToRoman(Integer.parseInt(expressionMembers[0]) * Integer.parseInt(expressionMembers[2]));
                }
                case "/" -> {
                    if (Integer.parseInt(expressionMembers[2]) == 0) {
                        throw new ArithmeticException("Деление на ноль не возможно");
                    }
                    return convertArabicToRoman(Integer.parseInt(expressionMembers[0]) / Integer.parseInt(expressionMembers[2]));
                }
            }
        }

        return result;
    }

    public static String convertArabicToRoman(int arabicNumber) {
        return switch (arabicNumber / 10) {
            case 0 -> romanNumb[arabicNumber - 1];
            case 1 -> arabicNumber % 10 == 0 ? romanNumb[9] : romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 2 ->
                    arabicNumber % 10 == 0 ? romanNumb[9] + romanNumb[9] : romanNumb[9] + romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 3 ->
                    arabicNumber % 10 == 0 ? romanNumb[9] + romanNumb[9] + romanNumb[9] : romanNumb[9] + romanNumb[9] + romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 4 ->
                    arabicNumber % 10 == 0 ? romanNumb[9] + romanHighNumb[0] : romanNumb[9] + romanHighNumb[0] + romanNumb[(arabicNumber % 10) - 1];
            case 5 -> arabicNumber % 10 == 0 ? romanHighNumb[0] : romanHighNumb[0] + romanNumb[(arabicNumber % 10) - 1];
            case 6 ->
                    arabicNumber % 10 == 0 ? romanHighNumb[0] + romanNumb[9] : romanHighNumb[0] + romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 7 ->
                    arabicNumber % 10 == 0 ? romanHighNumb[0] + romanNumb[9] + romanNumb[9] : romanHighNumb[0] + romanNumb[9] + romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 8 ->
                    arabicNumber % 10 == 0 ? romanHighNumb[0] + romanNumb[9] + romanNumb[9] + romanNumb[9] : romanHighNumb[0] + romanNumb[9] + romanNumb[9] + romanNumb[9] + romanNumb[(arabicNumber % 10) - 1];
            case 9 ->
                    arabicNumber % 10 == 0 ? romanNumb[9] + romanHighNumb[1] : romanNumb[9] + romanHighNumb[1] + romanNumb[(arabicNumber % 10) - 1];
            case 10 ->
                    arabicNumber % 10 == 0 ? romanHighNumb[1] : romanHighNumb[1] + romanNumb[(arabicNumber % 10) - 1];
            default -> " ";
        };
    }

    public static Boolean isArabicNumbers(String numb) {
        String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (String number : arabicNumbers) {
            if (numb.matches(number)) {
                return true;
            }
        }
        return false;
    }

    public static void checkOptimalNumber(int number) throws IOException {
        if (number > 10) {
            throw new IOException("Число больше 10, это не допусимо");
        }
    }
}