package ru.ifmo.cet.javabasics;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private static final int intCount = 99;
    private static final String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] numbers = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private int intBottleTakenAtOnce;
    private String stringBottleTakenAtOnce;
    private String s = ""; //окончание

    public BottleSong(int intBottleTakenAtOnce) {
        if (intBottleTakenAtOnce > 0 && intBottleTakenAtOnce < 100) {
            this.intBottleTakenAtOnce = intBottleTakenAtOnce;
            stringBottleTakenAtOnce = getStringNumber(intBottleTakenAtOnce);
        } else throw new IllegalArgumentException();
    }

    public String getBottleSongLyrics() {
        String str = "";
        int localIntCount = intCount;
        while (localIntCount > intBottleTakenAtOnce) {
            str += localIntCount + " bottles of beer on the wall, " + localIntCount + " bottles of beer.\n" +
                    "Take " + stringBottleTakenAtOnce + " down and pass around, ";
            localIntCount -= intBottleTakenAtOnce;
            if (localIntCount != 1) s = "s";
            str += localIntCount + " bottle" + s + " of beer on the wall.\n";
            s = "";
        }
        String finalStringBottleTakenAtOnce = getStringNumber(localIntCount);
        if (localIntCount != 1) s = "s";
        str += localIntCount + " bottle" + s + " of beer on the wall, " + localIntCount + " bottle" + s + " of beer.\n" +
                "Take " + finalStringBottleTakenAtOnce + " down and pass around, no more bottles of beer on the wall.\n" +
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
        s = "";
        return str;
    }

    private String getStringNumber(int number) {
        String str;
        if (number < 10) str = digits[number];
        else if (number < 20) str = numbers[number - 10];
        else {
            str = tens[number / 10];
            if (number % 10 != 0)
                str += " " + digits[number % 10];
        }
        return str;
    }

}