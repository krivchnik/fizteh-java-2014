package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import java.io.IOException;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.SimpleTableBuilder;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.TableUsingStrings;


public class MultifileTable extends TableUsingStrings {
	


    public MultifileTable(String directory, String tableName) {
        super(directory, tableName);
    }  

    /*private File getTableDirectory() {
		File tableDirectory = new File(getParentDirectory(), getName());
		if (!tableDirectory.exists()) {
	        tableDirectory.mkdir();
	    }
		return tableDirectory;
    }*/

	protected void load() throws IOException {
		MultiFileMapReadingUtils.load(new SimpleTableBuilder(this));
		
	}

	protected void save() throws IOException{
		MultiFileMapWritingUtils.save(new SimpleTableBuilder(this));
		
	}

}