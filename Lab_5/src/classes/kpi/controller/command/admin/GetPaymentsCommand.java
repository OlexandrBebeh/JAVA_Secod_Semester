package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.dao.entities.Payment;
import classes.kpi.controller.pagination.Pagination;
import classes.kpi.model.services.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class GetPaymentsCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        PaymentService paymentService = new PaymentService();
        ArrayList<Payment> payments = paymentService.getAll();
        if(req.getParameter("sort")!=null)
            paymentService.sort(payments,Integer.parseInt(req.getParameter("sort")));
        paymentService.close();
        Pagination.pagination(req,payments,"payments",10);
        return "/jsp/admin/payments.jsp";
    }
}
