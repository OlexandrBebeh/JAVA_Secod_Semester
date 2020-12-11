package project.kpi.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitCommand extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        req.getSession().invalidate();
        return null;
    }
}
