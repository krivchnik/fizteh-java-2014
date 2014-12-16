package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import java.util.HashMap;

import ru.fizteh.fivt.storage.strings.TableProvider;

public interface MultiFileMapTableProviderClass extends TableProvider {
	HashMap<String, Integer> showTables();
}
