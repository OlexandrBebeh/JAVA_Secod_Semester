package kpi.controll.commands.command;

import kpi.model.services.UserHolder;
import kpi.model.services.Users;
import kpi.view.ViewOutput;

public class ExitCommand extends AbstractCommand{
    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        userHolder.setUserEnum(Users.NONE);
        userHolder.setUserName("");
        userHolder.setPassword("");
    }

}
