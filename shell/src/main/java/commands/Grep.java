package commands;

import org.apache.commons.cli.*;

import java.util.List;

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

            boolean i = line.hasOption("i");
            boolean w = line.hasOption("w");
            int n     = -1;

            if (line.hasOption("A")) {
                n = Integer.parseInt(line.getOptionValue("A"));
            }

            return grep(args, i, w, n);
        }
        catch (ParseException e) {
            logger.Logger.log(e.getMessage());
            return null;
        }
    }

    private String grep(List<String> args, boolean i, boolean w, int n) {
        System.out.println(i + " " + w + " " + n);
        System.out.println(String.join(" ", args));

        return null;
    }
}
