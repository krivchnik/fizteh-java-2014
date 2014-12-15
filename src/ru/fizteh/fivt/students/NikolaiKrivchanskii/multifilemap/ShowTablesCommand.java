package ru.fizteh.fivt.students.NikolaiKrivchanskii.multifilemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.*;

public class ShowTablesCommand implements Commands<MultiFileMapShellState> {
	public String getCommandName() {
		return "show";
	}
	
	public int getArgumentQuantity() {
		return 1;
	}
	
	public void implement(String args, MultiFileMapShellState state) throws SomethingIsWrongException {
		ArrayList<String> parameters = Parser.parseCommandArgs(args);
		if(parameters.get(0) != "tables") {
			throw new SomethingIsWrongException("no command with this name");
		}
		HashMap<String, Integer> tables = state.tableProvider.showTables();
		for(Entry<String, Integer> iterator : tables.entrySet()) {
			System.out.println(iterator.getKey() + ' ' + iterator.getValue());
		}
	}
}