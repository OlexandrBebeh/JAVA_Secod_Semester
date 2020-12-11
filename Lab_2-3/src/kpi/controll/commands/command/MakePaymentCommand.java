package kpi.controll.commands.command;

import kpi.model.dao.Entities.Account;
import kpi.model.dao.Entities.Payment;
import kpi.model.services.AccountService;
import kpi.model.services.TransactionPayment;
import kpi.model.services.UserHolder;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class MakePaymentCommand extends AbstractCommand {
    AccountService accountService = new AccountService();
    TransactionPayment transactionPayment;

    @Override
    public void execute(ViewOutput view, UserHolder userHolder) {

        view.printMessage("Please, enter number of account:");

        ViewInput viewInput = new ViewInput(view);

        int accID = viewInput.getNumber();


        Account account = accountService.getAccount(
                " WHERE account_id = " + accID + " AND client_id = " + userHolder.getId());

        if(account == null){
            view.printMessage("This account don't exist!");
            return;
        }
        if(account.getBlocked()){
            view.printMessage("Account is blocked!");
            return;
        }

        double score;
        view.printMessage("Please, enter score:");
        score = viewInput.getDouble();
        if(score <= 0){
            view.printMessage("Score cant be less or equal 0");
            return;
        }
        String str;
        view.printMessage("Please, enter recipient account:");
        str = viewInput.getName();

        transactionPayment = new TransactionPayment();

        transactionPayment.doTransaction(account,
                new Payment.PaymentBuilder().setAccountID(accID).setScore(score).setRecipientAccount(str).build());

    }


}
