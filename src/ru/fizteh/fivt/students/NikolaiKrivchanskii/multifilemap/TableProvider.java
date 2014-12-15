package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import java.util.HashMap;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.*;

public interface TableProvider extends ru.fizteh.fivt.storage.strings.TableProvider{
    Table getTable(String a);
    Table createTable(String a);
    void removeTable(String a);
    HashMap<String, Integer> showTables();
}
