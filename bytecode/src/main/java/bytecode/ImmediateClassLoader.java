package bytecode;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.Files.readAllBytes;

public class ImmediateClassLoader extends ClassLoader {

    public ImmediateClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path classPath = getFullFilePath(name);
        byte[] bytes = readFile(classPath);

        return defineClass(name.replace("/", "."), bytes, 0, bytes.length);
    }

    private static byte[] readFile(Path path) {
        try {
            byte[] bytes = readAllBytes(path);
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException("Could not create stream from file");
        }
    }

    public static Path getFullFilePath(String fileName) {
        Objects.requireNonNull(fileName);
        String filePath = Objects.requireNonNull(ImmediateClassLoader.class.getClassLoader().getResource(fileName + ".class")).getPath();
        return Paths.get(filePath);
    }

   /* @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (resolve)
            resolveClass(c);
        return c;
    }*/
}
