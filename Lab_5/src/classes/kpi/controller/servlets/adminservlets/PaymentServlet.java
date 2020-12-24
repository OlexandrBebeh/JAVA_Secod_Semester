package classes.kpi.controller.servlets.adminservlets;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.command.admin.ConfirmPaymentCommand;
import classes.kpi.controller.command.admin.GetPaymentsCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value="/Admin/Payments")
public class PaymentServlet extends HomeServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new GetPaymentsCommand();
        String way = command.execute(req, resp);
        ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(way).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new ConfirmPaymentCommand();
        command.execute(req,resp);
        doGet(req, resp);
    }
}