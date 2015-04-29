package by.bsuir.library.logic;

import by.bsuir.library.bean.Request;
import by.bsuir.library.bean.Response;

/**
 * Created by Valentin on 29.04.2015.
 */
public interface ICommand {
    Response execute(Request request) throws LogicException;
}
