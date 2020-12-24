package classes.kpi.controller.command.admin;

import classes.kpi.controller.command.Command;
import classes.kpi.model.services.PaymentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfirmPaymentCommand extends Command {
    public static Logger logger = Logger.getLogger(ConfirmPaymentCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {


        if(req.getParameter("payment_id")!=null){
            PaymentService paymentService = new PaymentService();
            paymentService.confirmPayment(Integer.parseInt(req.getParameter("payment_id")));
            paymentService.close();
            logger.info("Payment number "+req.getParameter("payment_id") + " confirmed.");
        }

        return null;
    }
}
