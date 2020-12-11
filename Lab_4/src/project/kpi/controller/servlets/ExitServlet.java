package project.kpi.controller.servlets;

import project.kpi.controller.command.Command;
import project.kpi.controller.command.ExitCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Exit")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new ExitCommand();
        command.execute(req,resp);
        resp.sendRedirect("/Login");
    }
}
