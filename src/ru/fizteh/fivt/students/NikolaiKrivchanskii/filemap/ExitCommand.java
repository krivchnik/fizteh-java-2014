package ru.fizteh.fivt.students.NikolaiKrivchanskii.filemap;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomeCommand;
import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.SomethingIsWrongException;

public class ExitCommand<State extends FileMapShellStateInterface> extends SomeCommand<State>{

	public String getCommandName() {
		return "exit";
	}

	public int getArgumentQuantity() {
		return 0;
	}

	public void implement(String args, State state)
			throws SomethingIsWrongException {
		/*MyTable temp = (MyTable) state.getTable();*/
		if (state.getTable() != null /*&& !temp.getAutoCommit()*/) {
			state.rollback();
		}/* else if (temp.getAutoCommit() && state.getTable() != null) {
			state.commit();
		}*/
		throw new SomethingIsWrongException("EXIT");
	}

}
