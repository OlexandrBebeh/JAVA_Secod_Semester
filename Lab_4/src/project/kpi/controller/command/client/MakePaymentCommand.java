package project.kpi.controller.command.client;

import project.kpi.controller.command.Command;
import project.kpi.controller.validator.Validator;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.services.PaymentService;
import project.kpi.model.services.TransactionPayment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakePaymentCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        try{
            TransactionPayment transactionPayment = new TransactionPayment();
            PaymentService paymentService = new PaymentService();
            String score = req.getParameter("score");
            String recipient = req.getParameter("recipient");
            Validator.isDouble(score);
            double s = Double.parseDouble(score);
            Validator.isName(recipient);
            Account account = (Account) req.getSession().getAttribute("account");
            transactionPayment.doTransaction(account,paymentService.createPayment(account,s,recipient) );
            req.setAttribute("error","Payment created!");
        }catch (Exception e){
            req.setAttribute("error",e.getMessage());

        }
        return null;
    }
}
