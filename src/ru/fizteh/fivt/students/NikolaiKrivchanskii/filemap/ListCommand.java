package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

import java.util.ArrayList;
import java.util.List;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.*;

public class ListCommand implements Commands<FileMapShellState> {
    
    public String getCommandName() {
        return "list";
    }

    public int getArgumentQuantity() {
        return 0;
    }

    public void implement(String args, FileMapShellState state)
            throws SomethingIsWrongException {
        if (state.table == null) {
            throw new SomethingIsWrongException("Table not found.");
        }
        ArrayList<String> parameters = Parser.parseCommandArgs(args);
        if (parameters.size() != 0) {
            throw new SomethingIsWrongException("This command takes no parameters");
        }
        MyTable temp = (MyTable) state.table;
        List<String> keySet = temp.list();
        if (keySet.size() == 0) {
            System.out.println("\n");
            return;
        }
       
        StringBuilder sb = new StringBuilder("");
        for (String key : keySet) {
            sb.append(key);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

}
