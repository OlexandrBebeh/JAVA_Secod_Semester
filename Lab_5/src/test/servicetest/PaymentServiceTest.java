package test.servicetest;

import classes.kpi.model.dao.DAOPayment;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.dao.entities.Payment;
import classes.kpi.model.services.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
    DAOPayment daoPayment;

    @Before
    public void init(){
        daoPayment = mock(DAOPayment.class);
    }

    @Test
    public void getAllTest0(){
        PaymentService paymentService = new PaymentService(daoPayment);
        ArrayList<Payment> payments = new ArrayList<Payment>();
        when(daoPayment.getAll()).thenReturn(payments);

        ArrayList<Payment> res = paymentService.getAll();

        assertEquals(0,res.size());

        assertTrue(res.isEmpty());

        verify(daoPayment).getAll();
    }

    @Test
    public void getAllTest1(){
        PaymentService paymentService = new PaymentService(daoPayment);
        ArrayList<Payment> payments = new ArrayList<Payment>();

        for(int i = 0;i<10;i++){
            payments.add(
                    new Payment.PaymentBuilder()
                    .setPaymentID(i)
                    .setAccountID(i+1)
                    .setRecipientAccount("recipient"+i)
                    .setPaymentState("SENDED")
                    .setScore(1000*i)
                    .build()
            );
        }

        when(daoPayment.getAll()).thenReturn(payments);

        ArrayList<Payment> res = paymentService.getAll();

        assertEquals(10,res.size());

        assertEquals(payments.get(4).getPaymentID(),res.get(4).getPaymentID());

        verify(daoPayment).getAll();
    }

    @Test
    public void confirmPaymentTest0(){
        PaymentService paymentService = new PaymentService(daoPayment);

        paymentService.confirmPayment(1);

        verify(daoPayment).confirmPayment(1);
    }

    @Test
    public void getAccountPaymentsTest0(){
        PaymentService paymentService = new PaymentService(daoPayment);

        ArrayList<Payment> payments = new ArrayList<Payment>();
        for(int i = 0;i<10;i++){
            payments.add(
                    new Payment.PaymentBuilder()
                            .setPaymentID(i)
                            .setAccountID(1)
                            .setRecipientAccount("recipient"+i)
                            .setPaymentState("SENDED")
                            .setScore(1000*i)
                            .build()
            );
        }
        when(daoPayment.getWhere("WHERE account_id = 1")).thenReturn(payments);

        ArrayList<Payment> res = paymentService.getAccountPayments(1);

        assertEquals(10,res.size());
        assertEquals(payments.get(3).getPaymentID(),res.get(3).getPaymentID());
        verify(daoPayment).getWhere("WHERE account_id = 1");
    }

    @Test
    public void createPaymentTest0(){
        PaymentService paymentService = new PaymentService(daoPayment);

        Payment payment =
                    new Payment.PaymentBuilder()
                            .setRecipientAccount("recipient")
                            .setAccountID(1)
                            .setScore(1000)
                            .build();


        Payment res = paymentService.createPayment(new Account.AccountBuilder().setAccountID(1).build(), 1000,"recipient");


        assertEquals(payment.getAccountID(),res.getAccountID());
        assertEquals(payment.getScore(),res.getScore(),0);
        assertEquals(payment.getRecipientAccount(),res.getRecipientAccount());
    }

    @Test
    public void sortTest0(){
        PaymentService paymentService = new PaymentService(daoPayment);

        ArrayList<Payment> payments = new ArrayList<Payment>();
        for(int i = 0;i<10;i++){
            payments.add(
                    new Payment.PaymentBuilder()
                            .setPaymentID(i)
                            .setAccountID(1)
                            .setRecipientAccount("recipient"+i)
                            .setPaymentState("SENDED")
                            .setPaymentDate(new Date(2000,1,i%7))
                            .setScore(1000*i)
                            .build()
            );
        }

        ArrayList<Payment> res = paymentService.sort(payments,1);
        payments.sort(Comparator.comparing(Payment::getPaymentID));

        assertEquals(10,res.size());
        assertEquals(payments.get(3).getPaymentID(),res.get(3).getPaymentID());

        res = paymentService.sort(payments,2);
        payments.sort(Comparator.comparing(Payment::getPaymentDate));

        assertEquals(10,res.size());
        assertEquals(payments.get(3).getPaymentID(),res.get(3).getPaymentID());


        res = paymentService.sort(payments,3);
        Collections.reverse(payments);

        assertEquals(10,res.size());
        assertEquals(payments.get(3).getPaymentID(),res.get(3).getPaymentID());

    }
}
