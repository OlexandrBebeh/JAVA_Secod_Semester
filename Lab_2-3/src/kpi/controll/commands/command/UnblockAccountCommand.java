package kpi.controll.commands.command;

import kpi.model.services.AccountService;
import kpi.model.services.UserHolder;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class UnblockAccountCommand extends AbstractCommand{
    AccountService accountService = new AccountService();
    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printMessage("Please, enter number of account:");

        ViewInput viewInput = new ViewInput(view);

        int accID = viewInput.getNumber();

        accountService.setUnblocked(accID,userHolder);
    }


}
