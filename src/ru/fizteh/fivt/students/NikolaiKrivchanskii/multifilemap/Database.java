package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

public class Database implements TableProvider {
    HashMap<String, MultifileTable> content = new HashMap<String, MultifileTable>();
    private String databaseDirectoryPath;

    public Database(String databaseDirectoryPath) {
        this.databaseDirectoryPath = databaseDirectoryPath;
        File databaseDirectory = new File(databaseDirectoryPath);
        //if (databaseDirectory.getUsableSpace() != 0) {
        	for(File tableFile : databaseDirectory.listFiles()) {
            	if (tableFile!=null || tableFile.isFile()) {
                	continue;
            	}
            	MultifileTable table = new MultifileTable(databaseDirectoryPath, tableFile.getName());
            	content.put(table.getName(), table);
        	}
        //}
    }

    public MultifileTable getTable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Table's name cannot be null");
        }
        MultifileTable table = content.get(name);

        if (table == null) {
            return null;
        }
        if (table.getChangesCounter() > 0) {
            System.out.println("There are " + table.getChangesCounter() + " uncommited changes.");
        }

        return table;
    }

    public MultifileTable createTable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Table's name cannot be null");
        }
        if (content.containsKey(name)) {
            return null;
        }
        MultifileTable table = new MultifileTable(databaseDirectoryPath, name);
        content.put(name, table);
        return table;
    }

    public void removeTable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Table's name cannot be null");
        }

        if (!content.containsKey(name)) {
            throw new IllegalStateException("Table doesn't exist");
        }
        MultifileTable table = content.get(name);
        table.clear();
        table.commit();
        content.remove(name);

        File tableFile = new File(databaseDirectoryPath, name);
        tableFile.delete();
    }

	public HashMap<String, Integer> showTables() {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for (Entry<String, MultifileTable> contents : content.entrySet()) {
			result.put(contents.getKey(), contents.getValue().size());
		}
		return result;
	}
}
