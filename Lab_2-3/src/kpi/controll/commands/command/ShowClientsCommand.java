package kpi.controll.commands.command;

import kpi.model.services.ClientService;
import kpi.model.services.UserHolder;
import kpi.view.ViewOutput;

public class ShowClientsCommand extends AbstractCommand{
    ClientService clientService = new ClientService();
    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printClients(clientService.getAll());
    }


}
