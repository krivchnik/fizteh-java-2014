package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;


import java.io.File;
import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.storage.strings.TableProviderFactory;

public class DatabaseFactory implements TableProviderFactory {
    public TableProvider create(String directory) {
    	File databaseDirectory = new File(directory);
    	if (!databaseDirectory.exists()) {
    		databaseDirectory.mkdir();
    	}
        return new Database(databaseDirectory.getAbsolutePath());
    }
}
