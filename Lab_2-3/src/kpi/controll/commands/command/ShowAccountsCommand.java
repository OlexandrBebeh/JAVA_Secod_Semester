package kpi.controll.commands.command;

import kpi.model.services.AccountService;
import kpi.model.services.UserHolder;
import kpi.model.services.Users;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class ShowAccountsCommand extends AbstractCommand{

    AccountService accountService = new AccountService();
    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printMessage("Sort by:");
        view.printMessage("1 - name");
        view.printMessage("2 - score");
        view.printMessage("3 - number");

        ViewInput input = new ViewInput(view);

        int sort = input.getNumberOfCommand(1,3);

        if(userHolder.getUserEnum() == Users.ADMIN){
            view.printAccounts(accountService.sort(accountService.getAll(),sort));
        }else
            view.printAccounts(accountService.sort(accountService.getClientsAccount(userHolder.getId()),sort));
    }


}
