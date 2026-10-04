package com.apex.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileService {
    public void saveJson(String json, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Path parent = path.getParent();
        if  (parent != null) {
            Files.createDirectories(parent );
        }
        Files.writeString(path, json );
    }
}
