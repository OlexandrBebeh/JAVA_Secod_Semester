package project.kpi.model.services;

import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.DAOPayment;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.dao.entities.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PaymentService {

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


    public ArrayList<Payment> getAccountPayments(int account_id){
        DAOPayment daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments");

        ArrayList<Payment> payments =  daoPayment.getWhere("WHERE account_id = " + account_id);
        daoPayment.close();
        return payments;

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
        DAOPayment daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments");
        ArrayList<Payment> payments =  daoPayment.getAll();
        daoPayment.close();
        return payments;
    }
}
