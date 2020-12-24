package classes.kpi.controller.filters;

import org.apache.log4j.Logger;
import classes.kpi.model.dao.entities.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    public static Logger logger = Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if(session!=null && (session.getAttribute("admin"))!=null){
            logger.info(((Admin)session.getAttribute("admin")).getAdminName() + " go to " + req.getRequestURL().toString());
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            logger.info("Access denied. Redirect to Login.");
            res.sendRedirect(contextPath +"/Login");
        }
    }


    @Override
    public void destroy() {

    }
}
