package project.kpi.controller.servlets.clientservlets;

import project.kpi.controller.command.Command;
import project.kpi.controller.command.client.LoginCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Login")
public class LoginServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       ServletContext servletContext = getServletContext();
       RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/login.jsp");
       requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new LoginCommand();
        String way = command.execute(req,resp);
        if(way==null){
            doGet(req,resp);
        }else {
            resp.sendRedirect(way);
        }
    }

}
