package classes.kpi.model.services;


import classes.kpi.model.dao.*;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionPayment {
    Connection connection = null;
    DAOPayment daoPayment = null;
    DAOAccount daoAccount = null;

    public TransactionPayment() {
        connection = ConnectionPool.getConnection();
        daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments", connection);
        daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts", connection);
    }


    public TransactionPayment(Connection connection,AbstractDAO daoPayment,AbstractDAO DAOAccount) {
        this.connection = connection;
        this.daoPayment = (DAOPayment) daoPayment;
        this.daoAccount = (DAOAccount) DAOAccount;
    }

    public void doTransaction(Account account, Payment payment){

        try {
            connection.setAutoCommit(false);

            if (account.getScore() < payment.getScore()) {
                connection.rollback();
                throw new RuntimeException("In this account not enough money");
            } else {

                Account recipientAccount = daoAccount.getWhereOne("WHERE accountName = '" + payment.getRecipientAccount() + "'");

                if (recipientAccount == null||recipientAccount.getBlocked()) {
                    connection.rollback();
                    throw new RuntimeException("Recipient account don't exist or it's blocked!");

                }

                recipientAccount.setScore(recipientAccount.getScore() + payment.getScore());
                daoAccount.update(recipientAccount);
                account.setScore(account.getScore() - payment.getScore());
                daoAccount.update(account);

                payment.setPaymentState("PREPARED");
                daoPayment.insert(payment);

            }

            connection.commit();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
