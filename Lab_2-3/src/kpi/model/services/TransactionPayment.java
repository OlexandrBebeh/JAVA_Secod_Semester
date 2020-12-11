package kpi.model.services;

import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.ConnectionPool;
import kpi.model.dao.DAOAccount;
import kpi.model.dao.DAOPayment;
import kpi.model.dao.Entities.Account;
import kpi.model.dao.Entities.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionPayment {

    public void doTransaction(Account account, Payment payment) {

        Connection connection = ConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            DAOPayment daoPayment = (DAOPayment) AbstractDAOFactory.getDAO("payments", connection);
            DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts", connection);

            if (account.getScore() < payment.getScore()) {
                connection.rollback();
                throw new RuntimeException("In this account not enough money");
            } else {

                Account recipientAccount = daoAccount.getWhereOne(" WHERE accountName = '" + payment.getRecipientAccount() + "' ");

                if (recipientAccount == null||recipientAccount.getBlocked()) {
                    connection.rollback();
                    throw new RuntimeException("Recipient account don't exist or it's blocked!");

                }

                recipientAccount.setScore(recipientAccount.getScore() + payment.getScore());
                daoAccount.update(recipientAccount);
                account.setScore(account.getScore() - payment.getScore());
                daoAccount.update(account);

                payment.setPaymentState("SENDED");
                daoPayment.insert(payment);

            }

            connection.commit();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
