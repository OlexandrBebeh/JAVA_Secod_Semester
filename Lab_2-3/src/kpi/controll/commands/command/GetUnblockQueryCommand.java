package kpi.controll.commands.command;

import kpi.model.services.AccountService;
import kpi.model.services.UserHolder;
import kpi.view.ViewOutput;

public class GetUnblockQueryCommand extends AbstractCommand{

    private AccountService accountService = new AccountService();
    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printAccounts(accountService.getBlocked());
    }

}
