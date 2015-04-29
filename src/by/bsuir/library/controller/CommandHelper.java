package by.bsuir.library.controller;

import by.bsuir.library.logic.ICommand;
import by.bsuir.library.logic.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valentin on 29.04.2015.
 */
public class CommandHelper {
    private Map<ActionType, ICommand> commands = new HashMap<ActionType, ICommand>();
    private static CommandHelper instance = new CommandHelper();
    private CommandHelper() {
        commands.put(ActionType.ADD, new AddBookCommand());
        commands.put(ActionType.GET_ALL, new GetAllCommand());
        commands.put(ActionType.REMOVE, new RemoveBookCommand());
        commands.put(ActionType.WRITE_TO_XML, new WriteToXmlCommand());
        commands.put(ActionType.IMRORT_TO_DB, new ImportToDbCommand());
    }

    public ICommand getCommand(ActionType type) {
        return commands.get(type);
    }

    public static CommandHelper getInstance() {
        return instance;
    }
}
