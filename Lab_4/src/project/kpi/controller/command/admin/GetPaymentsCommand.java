package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Payment;
import project.kpi.model.services.PaginationService;
import project.kpi.model.services.PaymentService;

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
        PaginationService.pagination(req,payments,"payments",5);
        return "/jsp/admin/payments.jsp";
    }
}
