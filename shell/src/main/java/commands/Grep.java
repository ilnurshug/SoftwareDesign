package commands;

import com.google.common.io.Files;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Grep extends Command {
    private static Options options = new Options();
    private static CommandLineParser parser = new DefaultParser();

    static {
        options.addOption("i", false, "Ignore  case  distinctions  in  both  the  PATTERN and the input files.");

        options.addOption("w", false, "Select  only  those  lines  containing  matches  that form whole words.");

        options.addOption(OptionBuilder.withLongOpt("A")
                .withDescription( "Print  NUM  lines  of  trailing  context  after  matching lines." )
                .hasArg()
                .withArgName("NUM")
                .create());
    }

    public Grep() {
        super(-1);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        try {
            String[] argsArr = new String[args.size()];
            CommandLine line = parser.parse(options, args.toArray(argsArr));

            args = line.getArgList();

            if (args.size() < 2) {
                logger.Logger.log("not correct number of arguments");
                return null;
            }

            boolean i = line.hasOption("i");
            boolean w = line.hasOption("w");
            int n     = -1;

            if (line.hasOption("A")) {
                n = Integer.parseInt(line.getOptionValue("A"));
            }

            return grep(new GrepArgs(args.get(0), args.get(1), i, w, n));
        }
        catch (ParseException e) {
            logger.Logger.log(e.getMessage());
            return null;
        }
    }

    private String grep(GrepArgs args) {
        String patternString = args.getPattern();
        String filename = args.getFilename();

        if (args.isMatchWords()) {
            patternString = "\\b" + patternString + "\\b";
        }

        Pattern pattern;
        if (args.isIgnoreCase()) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        }
        else {
            pattern = Pattern.compile(patternString);
        }

        File file = new File(filename);
        List<File> files = new ArrayList<File>();
        if (file.isFile()) {
            files.add(file);
        }
        /*else if (file.isDirectory()) {
            files.addAll(FileUtils.listFiles(file, null, true));
        }*/
        else {
            logger.Logger.log(filename + " is not file or directory");
            return null;
        }

        return String.join(args.getAfterContext() > 0 ? "\n--\n" : "\n",
                                        files.stream()
                                        .map(f -> processFile(f, pattern, args))
                                        .collect(Collectors.toList())
        );
    }

    private String processFile(File file, Pattern pattern, GrepArgs args) {
        List<String> text = new ArrayList<>();
        try {
            text = Files.readLines(file, Charset.defaultCharset());
        }
        catch (IOException e) {
            logger.Logger.log(e.getMessage());
        }

        List<String> result = new ArrayList<>();
        for (int k = 0; k < text.size(); k++) {
            String line = text.get(k);
            if (args.isIgnoreCase()) {
                line = line.toLowerCase();
            }

            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                result.add(/*file.getName() +*/ ":" + text.get(k));
                for (int j = 1; k + j < text.size() && j <= args.getAfterContext(); j++) {
                    result.add(/*file.getName() + */"-" + text.get(k + j));
                }
            }
        }

        return String.join("\n", result);
    }

    private class GrepArgs {
        private String pattern;
        private String filename;
        private boolean ignoreCase;
        private boolean matchWords;
        private int afterContext;

        GrepArgs(String pattern, String filename, boolean ignoreCase, boolean matchWords, int afterContext) {
            this.pattern = pattern;
            this.filename = filename;
            this.ignoreCase = ignoreCase;
            this.matchWords = matchWords;
            this.afterContext = afterContext;
        }

        String getPattern() {
            return pattern;
        }

        String getFilename() {
            return filename;
        }

        boolean isIgnoreCase() {
            return ignoreCase;
        }

        boolean isMatchWords() {
            return matchWords;
        }

        int getAfterContext() {
            return afterContext;
        }
    }
}
