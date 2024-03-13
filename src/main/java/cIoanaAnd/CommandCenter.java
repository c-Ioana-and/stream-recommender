package cIoanaAnd;
/*
 *  Clasa care se ocupa cu retinerea si executarea comenzii curente
 *  Comenzile sunt: Add, ListC, Delete
 */

public class CommandCenter {
    private Command current_command;

    public void setCommand(Command command) {
        current_command = command;
    }

    public void executeCommand() {
        current_command.execute();
    }
}
