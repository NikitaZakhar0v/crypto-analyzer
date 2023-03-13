package ru.javarush.zakharov.cryptoanalyzer.modes;

import ru.javarush.zakharov.cryptoanalyzer.service.CoderService;

import java.io.File;

public abstract class Coder {
    private final long key;
    private final File file;
    private final Class<? extends Coder> aClass;

    public Coder(long key, File file) {
        this.key = key;
        this.file = file;
        this.aClass = this.getClass();
    }

    public void start(){
        CoderService.coding(key, file, aClass);
    }
}
