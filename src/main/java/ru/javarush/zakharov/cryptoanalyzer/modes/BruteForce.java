package ru.javarush.zakharov.cryptoanalyzer.modes;

import ru.javarush.zakharov.cryptoanalyzer.service.CoderService;

import java.io.File;

public class BruteForce {
    File file;
    public BruteForce(File file) {
        this.file = file;
    }

    public void start(){
        CoderService.decoderBruteForce(file);
    }
}
