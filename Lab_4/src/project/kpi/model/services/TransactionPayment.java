package project.kpi.model.services;

import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.ConnectionPool;
import project.kpi.model.dao.DAOAccount;
import project.kpi.model.dao.DAOPayment;
import project.kpi.model.dao.entities.Account;
import project.kpi.model.dao.entities.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionPayment {

    public void doTransaction(Account account, Payment payment){

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
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
