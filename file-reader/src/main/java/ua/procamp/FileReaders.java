package ua.procamp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileReaders} privides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        Path path = getFullFilePath(fileName);
        return readFile(path);
    }

    private static String readFile(Path path) {
        try (Stream<String> s = Files.lines(path)) {
            return s.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Could not create stream from file");
        }
    }

    private static Path getFullFilePath(String fileName) {
        Objects.requireNonNull(fileName);
        String filePath = Objects.requireNonNull(FileReaders.class.getClassLoader().getResource(fileName)).getPath();
        return Paths.get(filePath);
    }
}