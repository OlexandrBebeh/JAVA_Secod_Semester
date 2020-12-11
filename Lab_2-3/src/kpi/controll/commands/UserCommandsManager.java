package kpi.controll.commands;

import kpi.model.services.UserHolder;
import kpi.view.ViewOutput;

public abstract class UserCommandsManager {
    abstract public void setCommand(int command);

    public abstract void execute(ViewOutput viewOutput, UserHolder userHolder);
}
