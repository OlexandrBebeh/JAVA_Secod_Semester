package kpi.controll.commands.command;

import kpi.model.services.ClientService;
import kpi.model.services.UserHolder;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class BlockClientCommand extends AbstractCommand{
    ClientService clientService = new ClientService();

    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printMessage("Please, enter number of account:");

        ViewInput viewInput = new ViewInput(view);

        int id = viewInput.getNumber();

        clientService.blockClient(id);
    }

}
