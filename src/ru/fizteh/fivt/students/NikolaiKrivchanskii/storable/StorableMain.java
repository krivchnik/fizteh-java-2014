package ru.fizteh.fivt.students.NikolaiKrivchanskii.storable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap.CreateCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap.DropCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap.UseCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.CommitCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.ExitCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.GetCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.PutCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.RemoveKeyCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.RollbackCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Commands;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Shell;
import ru.fizteh.fivt.storage.structured.Storeable;
import ru.fizteh.fivt.storage.structured.Table;

public class StorableMain {
	public static void main(String[] args) {
		List<Commands<?>> commands = new ArrayList<Commands<?>>();
		HashSet<Commands<StorableShellState>> com =  new HashSet<Commands<StorableShellState>>() {{ 
		add(new ExitCommand<StorableShellState>()); 
		add(new RollbackCommand<StorableShellState>());
		add(new CommitCommand<StorableShellState>()); 
        add(new PutCommand<Table, String, Storeable, StorableShellState>()); 
        add(new GetCommand<Table, String, Storeable, StorableShellState>());
        add(new RemoveKeyCommand<Table, String, Storeable, StorableShellState>()); 
        add(new DropCommand<StorableShellState>());
        add(new UseCommand<Table, String, Storeable, StorableShellState>()); 
        add(new CreateCommand<Table, String, Storeable, StorableShellState>());}};
        commands.addAll(com);
        HashSet<Commands<?>> actualResult = new HashSet<Commands<?>>(commands);
		Shell<StorableShellState> shell = new Shell<StorableShellState>(actualResult);
		String databaseDirectory = System.getProperty("fizteh.db.dir");

        if (databaseDirectory == null) {
            System.err.println("You haven't set database directory");
            System.exit(1);
        }

        try {
            DatabaseTableProviderFactory factory = new DatabaseTableProviderFactory();
            StorableShellState shellState = new StorableShellState(factory.create(databaseDirectory));
            shell.setShellState(shellState);
        } catch (IOException e) {
            System.err.println("some error occurred during loading");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("error while loading: " + e.getMessage());
            System.exit(1);
        }
        shell.run(args, shell);
	}
}
