package visitors;

import commands.Grep;
import grammar.ShellParser;
import shell.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class GrepVisitor extends CommandVisitor<Grep, ShellParser.GrepContext> {
    public GrepVisitor() {
        super(Grep.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.GrepContext context) {
        visitor.visitChildren(context);

        List<String> args;

        args = CommandVisitor.getValue(context.literal());

        try {
            File temp = File.createTempFile("input", ".tmp");

            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            String prevCmdResult = Environment.INSTANCE.getPrevCmdResult();
            if (prevCmdResult != null) {
                bw.write(prevCmdResult);
            }
            bw.close();

            args.add(temp.getPath());
        }
        catch (IOException e) {
            logger.Logger.log("file creation error");
        }

        cmd.exec(args, visitor.isPipeCmd());
    }
}
