package commands;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Wc extends Command {

    public Wc() {
        super(-1);
    }

    @Override
    protected String execImpl(List<String> args) {
        if (args == null || args.size() == 0) {
            // TODO:
            return null;
        }
        else {
            List<FileInfo> infoList = args.stream()
                    .map(Wc::processFile)
                    .collect(Collectors.toList());

            int totalNlCnt = infoList.stream()
                    .mapToInt(FileInfo::getNewLinesCnt)
                    .sum();

            String res = String.join("\n", infoList.stream()
                                        .map(FileInfo::toString)
                                        .collect(Collectors.toList())
            );

            if (infoList.size() > 1) res += "\n" + totalNlCnt;

            return res;
        }
    }

    private static FileInfo processFile(String filename) {
        try {
            return new FileInfo(filename);
        }
        catch (IOException e) {
            logger.Logger.log("wc: " + filename + ": No such file");
            return new FileInfo(0, 0, 0);
        }
    }


    private static class FileInfo {
        private int newLinesCnt;
        private int wordsCnd;
        private int bytesCnt;

        public FileInfo(int newLinesCnt, int wordsCnd, int bytesCnt) {
            this.newLinesCnt = newLinesCnt;
            this.wordsCnd = wordsCnd;
            this.bytesCnt = bytesCnt;
        }

        public FileInfo(String filename) throws IOException {
            String text = readFile(filename, Charset.defaultCharset());

            newLinesCnt = count(text, '\n');
            wordsCnd = countWords(text);
            bytesCnt = text.getBytes().length;
        }

        public int getNewLinesCnt() {
            return newLinesCnt;
        }

        public int getWordsCnd() {
            return wordsCnd;
        }

        public int getBytesCnt() {
            return bytesCnt;
        }

        @Override
        public String toString() {
            return newLinesCnt + " " + wordsCnd + " " + bytesCnt;
        }

        private String readFile(String path, Charset encoding) throws IOException {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        }

        private int countWords(String s) {
            int wordCount = 0;

            boolean word = false;
            int endOfLine = s.length() - 1;

            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                    word = true;
                }
                else if (!Character.isLetter(s.charAt(i)) && word) {
                    wordCount++;
                    word = false;
                }
                else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                    wordCount++;
                }
            }

            return wordCount;
        }

        private int count(String s, char c) {
            int count = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == c) count++;
            return count;
        }

    }
}
