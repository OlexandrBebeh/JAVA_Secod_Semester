package classes.kpi.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitCommand extends Command{

    public static Logger logger = Logger.getLogger(ExitCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        logger.info("Exit");
        req.getSession().invalidate();
        return null;
    }
}
