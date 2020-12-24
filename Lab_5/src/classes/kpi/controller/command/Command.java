package classes.kpi.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
        public abstract String execute(HttpServletRequest req, HttpServletResponse res);
}
