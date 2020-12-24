package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.controller.validator.Validator;
import classes.kpi.model.dao.entities.Admin;
import classes.kpi.model.services.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    public static Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            AdminService adminService = new AdminService();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Validator.isName(username);
            Admin admin = adminService.getAdmin(username, password);
            adminService.close();
            if (admin == null) {
                req.setAttribute("error", "Wrong username or password");
                return null;
            } else {
                req.getSession().setAttribute("admin", admin);
                logger.info("Admin enter succeed. Name " + username);
                return "Admin/Home";
            }
        }catch (Exception e){
            logger.info("Admin enter failure.");
            req.setAttribute("error", e.getMessage());
        }
        return null;
    }
}
