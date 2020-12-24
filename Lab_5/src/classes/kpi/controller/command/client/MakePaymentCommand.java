package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.validator.Validator;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.services.PaymentService;
import classes.kpi.model.services.TransactionPayment;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakePaymentCommand extends Command {
    public static Logger logger = Logger.getLogger(MakePaymentCommand.class);
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
            logger.info("Transaction succeed. Score: " + score + " Recipient: " + recipient);
            paymentService.close();
        }catch (Exception e){
            logger.info("Transaction failed.");
            req.setAttribute("error",e.getMessage());

        }
        return null;
    }
}
