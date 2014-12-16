package ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class Shell<State> {
    private final Map<String, Commands> availableCommands;
    private static final String GREETING = "$ ";
    State state;
    
    
    public Shell(Set<Commands<?>> commands) {
        Map<String, Commands> tempCommands = new HashMap<String, Commands>();
        for (Commands<?> temp : commands) {
            tempCommands.put(temp.getCommandName(), temp);
        }
        availableCommands = Collections.unmodifiableMap(tempCommands);
    }
    
    public void setShellState(State state) {
        this.state = state;
    }
    
    private void runCommand(String[] data, State state) throws SomethingIsWrongException { 
        for (String command : data) {
            if (data[0].length() == 0) {
                throw new SomethingIsWrongException("Empty string.");
            }
            String name = Parser.getName(command);
            Commands<State> usedOne = availableCommands.get(name);
            String args = Parser.getParameters(command);
            if (usedOne == null) {
                throw new SomethingIsWrongException("Unknown command.");
            } 
            usedOne.implement(args, state);
            }
        }
    
    public void consoleWay(State state) {
        Scanner forInput = new Scanner(System.in);
        while (!Thread.currentThread().isInterrupted()) {    
            System.out.print(GREETING);
            try {
                String temp = forInput.nextLine();
                String[] commands = Parser.parseFullCommand(temp);
                runCommand(commands, state);                  
            } catch (SomethingIsWrongException exc) {
                if (exc.getMessage().equals("EXIT")) {
                    forInput.close();
                    System.exit(0);
                } else {
                    System.err.println(exc.getMessage());
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.err.println(e.getMessage());
            }
        }
        forInput.close();
    }
    
    
    public void run(String[] args, Shell<State> shell) {
        if (args.length != 0) {
            String arg = UtilMethods.uniteItems(Arrays.asList(args), " ");
            String[] commands = Parser.parseFullCommand(arg);
            try {
                runCommand(commands, state);                  
            } catch (SomethingIsWrongException exc) {
                if (exc.getMessage().equals("EXIT")) {
                    System.exit(0);
                } else {
                    System.err.println(exc.getMessage());
                    System.exit(-1);
                }
            }
        } else {
            consoleWay(state);
        }
        System.exit(0);
    }
    

}
