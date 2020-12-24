package classes.kpi.controller.servlets;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.command.ExitCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Exit")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new ExitCommand();
        command.execute(req,resp);
        resp.sendRedirect("/Lab_4/Login");
    }
}
