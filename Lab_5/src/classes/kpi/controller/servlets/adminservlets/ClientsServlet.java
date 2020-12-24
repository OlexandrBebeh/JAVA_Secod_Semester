package classes.kpi.controller.servlets.adminservlets;

import classes.kpi.controller.command.admin.BlockClientCommand;
import classes.kpi.controller.command.admin.GetClientsCommand;
import classes.kpi.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Admin/Clients")
public class ClientsServlet extends HomeServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new GetClientsCommand();
        String way = command.execute(req, resp);
        ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(way).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Command command = new BlockClientCommand();
        command.execute(req, resp);
        doGet(req, resp);
    }

}
