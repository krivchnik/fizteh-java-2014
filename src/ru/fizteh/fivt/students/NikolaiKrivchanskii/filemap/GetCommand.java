package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

import java.util.ArrayList;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomeCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomethingIsWrongException;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Parser;

public class GetCommand<Table, Key, Value, State extends FileMapShellStateInterface<Table, Key, Value>>
       extends SomeCommand<State> {

    public String getCommandName() {
        return "get";
    }

    public int getArgumentQuantity() {
        return 1;
    }
    
    public void implement(String args, State state)
            throws SomethingIsWrongException {
        if (state.getTable() == null) {
            throw new SomethingIsWrongException("no table");
        }
        ArrayList<String> parameters = Parser.parseCommandArgs(args);
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Use 1 argument for this operation");
        }
        Key key = state.parseKey(parameters.get(0));
        Value value = state.get(key);
        if (value == null) {
            System.out.println("not found");
        } else {
            System.out.println("found\n" + state.valueToString(value));
        }
        
    }
    

}
