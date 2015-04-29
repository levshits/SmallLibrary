package by.bsuir.library.bean;

import by.bsuir.library.controller.ActionType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valentin on 29.04.2015.
 */
public class Request {
    private ActionType actionType;
    private Map<ParameterType, Object> params = new HashMap<ParameterType, Object>();
    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
    public void setParam(ParameterType key, Object value){
        params.put(key, value);
    }

    public Object getParam(ParameterType key){
        return params.getOrDefault(key, null);
    }
}
