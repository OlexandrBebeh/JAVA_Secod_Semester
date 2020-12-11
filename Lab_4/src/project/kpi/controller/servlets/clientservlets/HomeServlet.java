package project.kpi.controller.servlets.clientservlets;

import project.kpi.controller.command.client.BLockAccountCommand;
import project.kpi.controller.command.client.HomeCommand;
import project.kpi.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value="/Client/Home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new HomeCommand();
        String way = command.execute(req,resp);
        ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(way).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Command command = new BLockAccountCommand();
        command.execute(req,resp);
        doGet(req, resp);
    }
}
