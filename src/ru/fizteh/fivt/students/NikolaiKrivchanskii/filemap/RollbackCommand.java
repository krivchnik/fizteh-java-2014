package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomeCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomethingIsWrongException;

public class RollbackCommand<State extends FileMapShellStateInterface> extends SomeCommand<State> {
    
    public String getCommandName() {
        return "rollback";
    }

    public int getArgumentQuantity() {
        return 0;
    }

    public void implement(String args, State state)
            throws SomethingIsWrongException {
        if (state.getTable() == null) {
            throw new SomethingIsWrongException("no table");
        }
        System.out.println(state.rollback());
    }

}
