package project.kpi.controller.servlets.adminservlets;

import project.kpi.controller.command.admin.BlockAccountCommand;
import project.kpi.controller.command.Command;
import project.kpi.controller.command.admin.GetUnblockQueryCommand;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Admin/Unblock-Query")
public class BlockQueryServlet extends HomeServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new GetUnblockQueryCommand();
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