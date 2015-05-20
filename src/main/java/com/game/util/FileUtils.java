package com.game.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Brought into existence by Eamon on 5/20/2015.
 */
public class FileUtils {

    final static Charset ENCODING = StandardCharsets.UTF_8;


    public static List<String> readSmallTextFile(String file) throws IOException {
        Path path = Paths.get(file);
        return Files.readAllLines(path, ENCODING);
    }

}
