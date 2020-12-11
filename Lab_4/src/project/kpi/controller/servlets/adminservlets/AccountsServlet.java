package project.kpi.controller.servlets.adminservlets;

import project.kpi.controller.command.admin.BlockAccountCommand;
import project.kpi.controller.command.admin.GetAccountsCommand;
import project.kpi.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Admin/Accounts")
public class AccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new GetAccountsCommand();
        String way = command.execute(req, resp);
        ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(way).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new BlockAccountCommand();
        command.execute(req, resp);
        doGet(req, resp);
    }
}