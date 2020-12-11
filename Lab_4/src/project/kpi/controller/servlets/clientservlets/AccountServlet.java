package project.kpi.controller.servlets.clientservlets;

import project.kpi.controller.command.client.AccountCommand;
import project.kpi.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value="/Client/Account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new AccountCommand();
        String way = command.execute(req, resp);
        ServletContext servletContext = req.getServletContext();
        servletContext.getRequestDispatcher(way).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
