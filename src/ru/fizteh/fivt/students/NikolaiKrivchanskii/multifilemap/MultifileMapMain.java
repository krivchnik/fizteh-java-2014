package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import java.util.ArrayList;
import java.util.HashSet;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.CommitCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.ExitCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.FileMapShellState;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.GetCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.ListCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.PutCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.RemoveKeyCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.RollbackCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.SizeCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Commands;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Shell;


public class MultifileMapMain {
    public static void main(String[] args) {
        HashSet<Commands<FileMapShellState>> com =  new HashSet<Commands<FileMapShellState>>() { {
            add(new ExitCommand()); add(new RollbackCommand());
            add(new CommitCommand()); add(new ListCommand());
        add(new PutCommand()); add(new GetCommand());
        add(new RemoveKeyCommand()); add(new SizeCommand()); }};
        HashSet<Commands<MultiFileMapShellState>> com1 =  new HashSet<Commands<MultiFileMapShellState>>() 
                { { add(new DropCommand()); add(new UseCommand()); 
         add(new CreateCommand()); }};
        ArrayList<Commands<?>> res = new ArrayList<Commands<?>>();
        res.addAll(com);
        res.addAll(com1);
        HashSet<Commands<?>> actualResult = new HashSet<Commands<?>>(res);
        Shell<MultiFileMapShellState> shell = new Shell<MultiFileMapShellState>(actualResult);
        try {
            String dbDirectory = System.getProperty("fizteh.db.dir");
            if (dbDirectory == null) {
                System.err.println("error: nope. Gimme something.");
                System.exit(-2);
            }
            MultiFileMapShellState state = new MultiFileMapShellState();
            DatabaseFactory factory = new DatabaseFactory();
            state.tableProvider = (MultiFileMapTableProviderClass) factory.create(dbDirectory);
            shell.setShellState(state);
        } catch (IllegalArgumentException e) {
            System.err.println("error: " + e.getMessage());
            System.exit(-1);
        }
        shell.run(args, shell);
    }
}
