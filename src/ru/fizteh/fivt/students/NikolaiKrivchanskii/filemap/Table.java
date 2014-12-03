package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

public interface Table extends ru.fizteh.fivt.storage.strings.Table {
    String getName();
    String get(String a);
    String put(String a, String b);
    String remove(String a);
    int size();
    int commit();
    int rollback();
}
