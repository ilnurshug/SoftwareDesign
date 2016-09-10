package commands;

import logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Cat extends Command {
    public Cat() {
        super(-1);
    }

    @Override
    public String execImpl(List<String> args) {
        return String.join("\n",
                args.stream()
                        .map(Cat::processFile)
                        .collect(Collectors.toList())
        );
    }

    private static String processFile(String filename) {
        try {
            return readFile(filename, Charset.defaultCharset());
        }
        catch (IOException e) {
            Logger.log("file " + e.getMessage() + "not found");
            return "";
        }
    }

    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
