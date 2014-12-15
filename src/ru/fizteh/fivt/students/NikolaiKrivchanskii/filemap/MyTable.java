package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

import java.util.List;


public interface MyTable extends Table {
    String getName();
    void setAutoCommit(boolean status);
    boolean getAutoCommit();
    int getChangesCounter();
    List<String> list();
    String get(String a);
    String put(String key, String value);
    String remove(String a);
    int size();
    int commit();
    int rollback();
}
