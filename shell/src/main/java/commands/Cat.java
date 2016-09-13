package commands;

import logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import com.google.common.io.Files;
import java.util.List;
import java.util.stream.Collectors;

/*
    Read file's text
 */
public class Cat extends Command {
    public Cat() {
        super(-1);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        return String.join("\n",
                args.stream()
                        .map(Cat::processFile)
                        .collect(Collectors.toList())
        );
    }

    private static String processFile(String filename) {
        try {
            return Files.toString(new File(filename), Charset.defaultCharset());
        }
        catch (IOException e) {
            Logger.log("file " + e.getMessage() + " not found");
            return null;
        }
    }
}
