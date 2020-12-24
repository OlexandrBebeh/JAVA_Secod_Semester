package test.servicetest;
import classes.kpi.model.dao.DAOAccount;
import classes.kpi.model.dao.DAOPayment;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Payment;
import classes.kpi.model.services.TransactionPayment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionPaymentTest {
    DAOPayment daoPayment;
    Connection connection;
    DAOAccount daoAccount;

    @Before
    public void init(){
        daoAccount = mock(DAOAccount.class);
        daoPayment = mock(DAOPayment.class);
        connection = mock(Connection.class);
    }

    @Test
    public void TransactionTest(){
        TransactionPayment transactionPayment = new TransactionPayment(connection,daoPayment,daoAccount);

        Payment payment =
                new Payment.PaymentBuilder()
                        .setRecipientAccount("recipient")
                        .setAccountID(1)
                        .setScore(1000)
                        .build();
        Account account =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();
        Account recipient =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("recipient")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 2 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();


        when(daoAccount.getWhereOne("WHERE accountName = 'recipient'")).thenReturn(recipient);

        transactionPayment.doTransaction(account,payment);

        assertEquals(2000,recipient.getScore(),0);
        assertEquals(0,account.getScore(),0);

        verify(daoAccount,times(2)).update(any());

    }

    @Test(expected = RuntimeException.class)
    public void TransactionTest_NotHaveMoney() {
        TransactionPayment transactionPayment = new TransactionPayment(connection,daoPayment,daoAccount);

        Payment payment =
                new Payment.PaymentBuilder()
                        .setRecipientAccount("recipient")
                        .setAccountID(1)
                        .setScore(1000)
                        .build();
        Account account =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(999)
                        .setBlocked(false)
                        .build();
        Account recipient =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("recipient")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 2 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();


        when(daoAccount.getWhereOne("WHERE accountName = 'recipient'")).thenReturn(recipient);

        transactionPayment.doTransaction(account,payment);

    }

    @Test(expected = RuntimeException.class)
    public void TransactionTest_RecipientNotExist() {
        TransactionPayment transactionPayment = new TransactionPayment(connection,daoPayment,daoAccount);

        Payment payment =
                new Payment.PaymentBuilder()
                        .setRecipientAccount("recipient")
                        .setAccountID(1)
                        .setScore(1000)
                        .build();
        Account account =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();
        Account recipient = null;


        when(daoAccount.getWhereOne("WHERE accountName = 'recipient'")).thenReturn(recipient);

        transactionPayment.doTransaction(account,payment);

    }

    @Test(expected = RuntimeException.class)
    public void TransactionTest_ConnectionLost() throws SQLException {
        TransactionPayment transactionPayment = new TransactionPayment(connection,daoPayment,daoAccount);

        Payment payment =
                new Payment.PaymentBuilder()
                        .setRecipientAccount("recipient")
                        .setAccountID(1)
                        .setScore(1000)
                        .build();
        Account account =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();
        Account recipient =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("recipient")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 2 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();


        when(daoAccount.getWhereOne("WHERE accountName = 'recipient'")).thenReturn(recipient);

        doThrow(new SQLException("Commit lost!")).when(connection).commit();

        transactionPayment.doTransaction(account,payment);

    }

    @Test(expected = RuntimeException.class)
    public void TransactionTest_RecipientBlocked() {
        TransactionPayment transactionPayment = new TransactionPayment(connection,daoPayment,daoAccount);

        Payment payment =
                new Payment.PaymentBuilder()
                        .setRecipientAccount("recipient")
                        .setAccountID(1)
                        .setScore(1000)
                        .build();
        Account account =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();
        Account recipient = new Account.AccountBuilder()
                .setAccountID(2)
                .setAccountName("recipient")
                .setClientID(3)
                .setCreditCardNumber("0000-" + 2 + "000-0000-1111")
                .setScore(1000)
                .setBlocked(true)
                .build();


        when(daoAccount.getWhereOne("WHERE accountName = 'recipient'")).thenReturn(recipient);

        transactionPayment.doTransaction(account,payment);

    }
}
