package classes.kpi.model.services;

import classes.kpi.model.dao.AbstractDAO;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOPayment;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PaymentService {
    DAOPayment daoPayment = null;

    public PaymentService() {
        daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments");
    }


    public PaymentService(AbstractDAO dao) {
        daoPayment = (DAOPayment)dao;
    }

    public void close(){
        daoPayment.close();
    }

    public Payment createPayment(Account account, double score,String recipient){
        Payment payment = null;
        if(account!=null) {
            payment = new Payment.PaymentBuilder()
                    .setAccountID(account.getAccountID())
                    .setScore(score)
                    .setRecipientAccount(recipient)
                    .build();

        }
        return payment;
    }

    public void confirmPayment(int id){
        daoPayment.confirmPayment(id);
    }


    public ArrayList<Payment> getAccountPayments(int account_id){
        return daoPayment.getWhere("WHERE account_id = " + account_id);
    }

    public ArrayList<Payment> sort(ArrayList<Payment> payments,int sort){
        if(sort == 1){
            payments.sort(Comparator.comparing(Payment::getPaymentID));
        } else if(sort == 2){
            payments.sort(Comparator.comparing(Payment::getPaymentDate));
        }else if (sort == 3){
            payments.sort(Comparator.comparing(Payment::getPaymentDate));
            Collections.reverse(payments);
        }

        return payments;
    }

    public ArrayList<Payment> getAll(){
        return daoPayment.getAll();
    }
}
