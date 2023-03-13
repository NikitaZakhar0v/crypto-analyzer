package ru.javarush.zakharov.cryptoanalyzer.service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileService {

    static File createNewOutputFile(File fileInput, Class<?> clazz) {
        final String path = fileInput.getParent();
        final String name = fileInput.getName().replace("Encoder_", "").replace("Decoder_", "");
        final File fileOutput = new File(path + "//" + clazz.getSimpleName() + "_" + name);
        try {
            if (fileOutput.createNewFile()) {
                System.out.println("Создан новый файл с зашифрованным текстом: " + fileOutput.getName() + "\n" +
                        "Абсолютный путь: " + fileOutput.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileOutput;
    }

    static void writeToOutputFile(String text, File toFile) {
        try (final FileOutputStream fileOutputStream = new FileOutputStream(toFile)) {
            Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String readeFile(File file) {
        final StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        try (InputStream inputStream = new FileInputStream(file);
             Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            for (int i = reader.read(buffer); i >= 0; i = reader.read(buffer)) {
                stringBuilder.append(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
