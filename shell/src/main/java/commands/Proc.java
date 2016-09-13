package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Proc extends Command {
    public Proc() {
        super(-1);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        List<String> output = new LinkedList<>();

        ProcessBuilder pb = new ProcessBuilder(args);
        try {
            Process process = pb.start();

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                output.add(line);
            }
        }
        catch (IOException e) {
            logger.Logger.log(e.getMessage());
        }

        return String.join("\n", output);
    }
}
