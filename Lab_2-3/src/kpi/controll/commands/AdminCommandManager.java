package kpi.controll.commands;

import kpi.controll.commands.command.*;
import kpi.model.services.UserHolder;
import kpi.view.ViewOutput;

public class AdminCommandManager extends UserCommandsManager {
    AbstractCommand command;
    @Override
    public void setCommand(int command) {

        switch (command){
            case 1 -> this.command = new BlockClientCommand();
            case 2 -> this.command = new UnblockClientCommand();
            case 3 -> this.command = new ShowClientsCommand();
            case 4 -> this.command = new ShowAccountsCommand();
            case 5 -> this.command = new ShowPaymentsCommand();
            case 6 -> this.command = new BlockAccountCommand();
            case 7 -> this.command = new UnblockAccountCommand();
            case 8 -> this.command = new GetUnblockQueryCommand();
            case 9 -> this.command = new ExitCommand();
            default ->
                    throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    public void execute(ViewOutput viewOutput, UserHolder userHolder){
        this.command.execute(viewOutput,userHolder);
    }
}
