package kpi.controll.commands.command;

import kpi.model.services.UserHolder;
import kpi.view.ViewOutput;

public abstract class AbstractCommand {

    abstract public void execute(ViewOutput view, UserHolder userHolder);
}
