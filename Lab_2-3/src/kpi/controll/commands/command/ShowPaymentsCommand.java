package kpi.controll.commands.command;

import kpi.model.services.PaymentService;
import kpi.model.services.UserHolder;
import kpi.model.services.Users;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class ShowPaymentsCommand extends AbstractCommand {

    private PaymentService paymentService = new PaymentService();


    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {
        view.printMessage("Sort by:");
        view.printMessage("1 - by number");
        view.printMessage("2 - by newest date");
        view.printMessage("3 - by latest date");

        ViewInput input = new ViewInput(view);

        int sort = input.getNumberOfCommand(1,3);
        if(userHolder.getUserEnum()== Users.ADMIN){
            view.printPayments(paymentService.sort(paymentService.getAll(),sort));
        }else
            view.printPayments(paymentService.sort(paymentService.getClientPayments(userHolder),sort));
    }


}
