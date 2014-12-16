package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;


import java.io.IOException;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.FileMapShellState;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap.MyTable;

public class MultiFileMapShellState extends FileMapShellState 
       implements MultifileMapShellStateInterface<MyTable, String, String> {
        public MultiFileMapTableProviderClass tableProvider;

        public MyTable useTable(String name) {
            MyTable tempTable = (MyTable) tableProvider.getTable(name);
            if (tempTable != null) {
                table = tempTable;
            }
            return tempTable;
        }

        public MyTable createTable(String args) {
            return (MyTable) tableProvider.createTable(args);
        }

        public void dropTable(String name) throws IOException {
            tableProvider.removeTable(name);
            if (table.getName().equals(name)) {
                table = null;
            }
        }

        public String getCurrentTableName() {
            return table.getName();
        }

        @Override
        public String put(String key, String value) {
            return table.put(key, value);
        }

        @Override
        public String get(String key) {
            return table.get(key);
        }

        @Override
        public int commit() {
            return table.commit();
        }

        @Override
        public int rollback() {
            return table.rollback();
        }

        @Override
        public int size() {
            return table.size();
        }

        @Override
        public String remove(String key) {
            return table.remove(key);
        }

        @Override
        public MyTable getTable() {
            return (MyTable) table;
        }

        @Override
        public String keyToString(String key) {
            return key;
        }

        @Override
        public String valueToString(String value) {
            return value;
        }

        @Override
        public String parseKey(String key) {
            return key;
        }
        
        @Override
        public String parseValue(String value) {
            return value;
        }    
}

