package ru.javarush.zakharov.cryptoanalyzer.service;

import ru.javarush.zakharov.cryptoanalyzer.modes.BruteForce;
import ru.javarush.zakharov.cryptoanalyzer.modes.Coder;
import ru.javarush.zakharov.cryptoanalyzer.modes.Decoder;

import java.io.File;
import java.util.*;

import static ru.javarush.zakharov.cryptoanalyzer.service.AlphabetService.getAlphabet;
import static ru.javarush.zakharov.cryptoanalyzer.service.FileService.*;

public class CoderService {
    private static final Map<Character, Integer> mapInput = new HashMap<>();
    private static final Map<Integer, Character> mapOutput = new HashMap<>();
    private static final char[] alphabet = getAlphabet();

    static {
        fillHashMap();
    }

    private static void fillHashMap() {
        for (int i = 0; i < alphabet.length; i++) {
            mapInput.put(alphabet[i], i);
            mapOutput.put(i, alphabet[i]);
        }
    }


    public static void coding(long key, File file, Class<? extends Coder> clazz) {
        key = updateKey(key, clazz);
        String inputText = readeFile(file);
        final String text = algorithmCoding(inputText.toCharArray(), key);
        final File fileOutput = createNewOutputFile(file, clazz);
        writeToOutputFile(text, fileOutput);
    }

    private static String algorithmCoding(char[] array, long key) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            final int i1 = (int) (mapInput.get(array[i]) + (key % alphabet.length));
            result[i] = mapOutput.get(i1 % alphabet.length);
        }
        return String.valueOf(result);
    }

    private static long updateKey(long key, Class<? extends Coder> clazz) {
        key %= alphabet.length;
        if (clazz.equals(Decoder.class)) {
            key = alphabet.length - key;
        }
        return key == 0 ? 1 : key;
    }

    public static void decoderBruteForce(File file){
        final List<String> checkWordList = getBruteForceCollection();
        String text = readeFile(file);
        boolean ready = false;
        String testResult = "";
        for (long i = 0; !ready; i++) {
            testResult = algorithmCoding(text.toCharArray(), i);
            String[] textArray = testResult.toLowerCase().split(" ");
            ready = Arrays.asList(textArray).containsAll(checkWordList);
            if(i == alphabet.length){
                System.out.println("Не удалось распознать текст");
                break;
            }
        }
        final File fileOutput = createNewOutputFile(file, BruteForce.class);
        writeToOutputFile(testResult, fileOutput);
    }

    private static List<String> getBruteForceCollection(){
        return Arrays.asList("с", "на", "и", "о", "в");
    }

}
//  /\,/