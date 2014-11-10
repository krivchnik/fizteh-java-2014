package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;


import java.io.File;

import ru.fizteh.fivt.storage.strings.TableProvider;



public class DatabaseFactory implements MultiFileMapTableProviderClassFactory {
    public TableProvider create(String directory) {
    	if (directory == null || directory.isEmpty() ) {
    		throw new IllegalArgumentException ("directory name cannot be null");
    	}
    	File databaseDirectory = new File(directory);
    	if (databaseDirectory.isFile()) {
    		throw new IllegalArgumentException ("it must be directory, not file");
    	}
    	if (!databaseDirectory.exists()) {
    		databaseDirectory.mkdir();
    	}
        return new Database(databaseDirectory.getAbsolutePath());
    }
}
