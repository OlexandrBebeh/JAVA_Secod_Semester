package project.kpi.controller.command.client;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.dao.entities.Payment;
import project.kpi.model.services.AccountService;
import project.kpi.model.services.PaginationService;
import project.kpi.model.services.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AccountCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        PaymentService paymentService = new PaymentService();
        AccountService accountService = new AccountService();

        ArrayList<Account> accounts = (ArrayList<Account>)req.getSession().getAttribute("accounts");
        Account account = null;
        for (Account acc:accounts) {
            if(acc.getAccountName().equals(req.getParameter("AccountName"))){
                account = accountService.getAccount(" WHERE account_id = " + acc.getAccountID());
            }
        }

        if(account!=null) {
            if(!account.getBlocked()) {

                ArrayList<Payment> payments = paymentService.getAccountPayments(account.getAccountID());

                if(req.getParameter("sort")!=null) {
                    paymentService.sort(payments,Integer.parseInt(req.getParameter("sort")));
                }

                PaginationService.pagination(req,payments,"payments",5);

                req.getSession().setAttribute("account", account);
            }
            else{
                req.setAttribute("error","Account is blocked!");
            }
        }else{
            req.setAttribute("error","Wrong account!");
        }
        return  "/jsp/account.jsp";
    }
}
