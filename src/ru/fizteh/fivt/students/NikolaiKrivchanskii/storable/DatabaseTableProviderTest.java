package ru.fizteh.fivt.students.NikolaiKrivchanskii.storable;


import org.junit.Test;

import ru.fizteh.fivt.storage.structured.TableProviderFactory;

import java.io.IOException;

public class DatabaseTableProviderTest {
    TableProviderFactory factory = new DatabaseTableProviderFactory();

    @Test(expected = IOException.class)
    public void createProviderUnavailableShouldFail() throws IOException {
        factory.create("M:\\");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProviderEmptyShouldFail() {
        try {
            factory.create("");
        } catch (IOException e) {
            System.out.println("Exception caught");
        }
    }
}
