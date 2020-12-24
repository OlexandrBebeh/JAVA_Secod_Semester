package classes.kpi.controller.filters;

import org.apache.log4j.Logger;
import classes.kpi.model.dao.entities.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnterFilter implements Filter {

    public static Logger logger = Logger.getLogger(EnterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if(session!=null && (session.getAttribute("client"))!=null){
            logger.info(((Client)session.getAttribute("client")).getClientName() + " go to " + req.getRequestURL().toString());
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            logger.info("Unauthorized user. Redirect to Login.");
            res.sendRedirect(contextPath +"/Login");
        }
    }


    @Override
    public void destroy() {

    }
}
