package ru.javarush.zakharov.cryptoanalyzer.service;

import ru.javarush.zakharov.cryptoanalyzer.modes.BruteForce;
import ru.javarush.zakharov.cryptoanalyzer.modes.Decoder;
import ru.javarush.zakharov.cryptoanalyzer.modes.Encoder;

import java.io.*;

public class Menu {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() {
        String input = "";
        while (!input.equals("4")) {

            System.out.println("""
                    Выберите пункт меню:
                    1. Зашифровать текст
                    2. Расшифровать текст (требуется криптографический ключ)
                    3. Расшифровать методом "Bruteforce"
                    4. Выход""");

            input = getInput();

            switch (input) {
                case "1" -> new Encoder(getKey(), getFileFromPath())
                        .start();
                case "2" -> new Decoder(getKey(), getFileFromPath())
                        .start();
                case "3" -> new BruteForce(getFileFromPath())
                        .start();
                case "4" -> System.out.println("Exit");
            }
        }
    }

    private String getInput() {
        String str;
        try {
            str = reader.readLine();
            if (str.length() > 1 || !Character.isDigit(str.charAt(0)) || Integer.parseInt(str) > 4 || Integer.parseInt(str) < 1) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("Введен некорректный формат");
            return getInput();
        }
        return str;
    }

    private long getKey() {
        System.out.println("Введите числовой криптографический ключ:");
        long key;
        try {
            key = Long.parseLong(reader.readLine());
        } catch (Exception e) {
            System.out.println("Введеный ключ должен состоять только из цифр");
            return getKey();
        }
        return key;
    }

    private File getFileFromPath() {
        System.out.println("Введите абсолютный (полный) путь к текстовому файлу:");
        File file = null;
        try {
            file = new File(reader.readLine());
            if (!file.isFile()) {
                throw new FileNotFoundException();
            } else if (!isFileExtensionTxt(file)) {
                throw new IllegalArgumentException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Данного файла не существует");
            return getFileFromPath();
        } catch (IllegalArgumentException e) {
            System.out.println("Данный файл должен иметь расширение \".txt\"");
            return getFileFromPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private boolean isFileExtensionTxt(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1).equals("txt");
        }
        return false;
    }
}