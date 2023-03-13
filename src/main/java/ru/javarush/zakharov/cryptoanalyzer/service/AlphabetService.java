package ru.javarush.zakharov.cryptoanalyzer.service;

public class AlphabetService {

    static char[] getAlphabet() {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ., ?!\"'()[]%;-+/\r\n:—…1234567890";
        System.out.println(alphabet.length());
        return alphabet.toCharArray();
    }
}
