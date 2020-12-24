package classes.kpi.controller.command.client;

import classes.kpi.controller.command.Command;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Payment;
import classes.kpi.model.services.AccountService;
import classes.kpi.controller.pagination.Pagination;
import classes.kpi.model.services.PaymentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class AccountCommand extends Command {

    public static Logger logger = Logger.getLogger(AccountCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AccountService accountService = new AccountService();

        Account account = (Account)req.getSession().getAttribute("account");
        account = accountService.getAccount(" WHERE account_id = " + account.getAccountID());
        accountService.close();
        if(account!=null) {
            if(!account.getBlocked()) {
                PaymentService paymentService = new PaymentService();
                ArrayList<Payment> payments = paymentService.getAccountPayments(account.getAccountID());

                if(req.getParameter("sort")!=null) {
                    paymentService.sort(payments,Integer.parseInt(req.getParameter("sort")));
                }
                paymentService.close();
                Pagination.pagination(req,payments,"payments",5);

                req.getSession().setAttribute("account", account);
            }
            else{
                logger.info("Try to call blocked account:" + account.getAccountName());
                req.setAttribute("error","Account is blocked!");
            }
        }else{
            logger.info("Try to call not exist account");
            req.setAttribute("error","Wrong account!");
        }
        return  "/jsp/account.jsp";
    }
}
