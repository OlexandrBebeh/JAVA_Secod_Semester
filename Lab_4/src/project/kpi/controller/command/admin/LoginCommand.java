package project.kpi.controller.command.admin;

import project.kpi.controller.command.Command;
import project.kpi.model.dao.entities.Admin;
import project.kpi.model.services.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        AdminService adminService = new AdminService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = adminService.getAdmin(username,password);
        if(admin==null) {
            req.setAttribute("error", "Wrong username or password");
            return null;
        }
        else{
            req.getSession().setAttribute("admin", admin);
            return "Admin/Home";
        }
    }
}
