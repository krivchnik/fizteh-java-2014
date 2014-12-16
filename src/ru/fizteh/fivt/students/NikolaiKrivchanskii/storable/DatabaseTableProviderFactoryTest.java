package ru.fizteh.fivt.students.NikolaiKrivchanskii.storable;

import org.junit.Test;

import ru.fizteh.fivt.storage.structured.TableProviderFactory;

import java.io.IOException;

public class DatabaseTableProviderFactoryTest {
    @Test(expected = IllegalArgumentException.class)
    public void createProviderNullDirectoryTest() {
        TableProviderFactory factory = new DatabaseTableProviderFactory();
        try {
            factory.create(null);
        } catch (IOException e) {
            //
        }
    }
}