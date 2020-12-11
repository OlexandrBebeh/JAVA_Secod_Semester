package kpi.model.services;

import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.ConnectionPool;
import kpi.model.dao.DAOAccount;
import kpi.model.dao.DAOPayment;
import kpi.model.dao.Entities.Account;
import kpi.model.dao.Entities.Client;
import kpi.model.dao.Entities.Payment;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PaymentService {
    private DAOPayment daoPayment;
    private DAOAccount daoAccount;

    public PaymentService(){
        Connection con = ConnectionPool.getConnection();
        daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts",con);
        daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments",con);
    }

    public ArrayList<Payment> getClientPayments(UserHolder uh){

        ClientService clientService = new ClientService();

        Client client = clientService.getClient(uh);

        if(client.getBlocked()) throw new RuntimeException();

        ArrayList<Account> accounts = daoAccount.getWhere(" WHERE client_id = " + client.getClientID());

        if(accounts!= null && !accounts.isEmpty()){

            StringBuilder str = new StringBuilder();
            for (Account acc: accounts) {
                str.append( acc.getAccountID());
                str.append(",");
            }

            String req = " WHERE account_id in (" + str.substring(0,str.length()-1) +") ";

            return daoPayment.getWhere(req);
        } else  throw new RuntimeException();
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
