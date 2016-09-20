package commands;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import com.google.common.io.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * count newlines, words and bytes for each file
 */
public class Wc extends Command {

    public Wc() {
        super(-1);
    }

    /**
     * @param args list of filenames
     * @param inPipe true if current command is being executed in pipe
     * @return number of newlines, words and bytes for each file joined by newlines
     */
    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        if (args.size() == 0) {
            // TODO:
            return null;
        }
        else {
            List<FileInfo> infoList = args.stream()
                    .map(Wc::processFile)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return String.join("\n", infoList.stream()
                                        .map(FileInfo::toString)
                                        .collect(Collectors.toList())
            );
        }
    }

    private static FileInfo processFile(String filename) {
        try {
            return new FileInfo(filename);
        }
        catch (IOException e) {
            logger.log("wc: " + filename + ": No such file");
            return null;
        }
    }

    private static class FileInfo {
        private String filename;
        private int newLinesCnt;
        private int wordsCnd;
        private int bytesCnt;

        FileInfo(String filename) throws IOException {
            this.filename = filename;

            List<String> lines = Files.readLines(new File(filename), Charset.defaultCharset());
            String text = String.join("\n", lines);

            newLinesCnt = lines.size();
            wordsCnd = lines.stream().mapToInt(s -> s.split("\\W+").length).sum();
            bytesCnt = text.getBytes().length;
        }

        @Override
        public String toString() {
            return newLinesCnt + " " + wordsCnd + " " + bytesCnt + " " + filename;
        }
    }
}
