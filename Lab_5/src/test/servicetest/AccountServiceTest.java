package test.servicetest;

import classes.kpi.model.dao.DAOAccount;
import classes.kpi.model.dao.entities.Account;
import classes.kpi.model.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    DAOAccount daoAccount;


    @Before
    public void init(){
        daoAccount = mock(DAOAccount.class);
    }

    @Test
    public void getAllTest0(){
        AccountService accountService = new AccountService(daoAccount);

        ArrayList<Account> arr =  new ArrayList<Account>();

        when(daoAccount.getAll()).thenReturn(arr);

        ArrayList<Account> query = accountService.getAll();
        assertEquals(arr, query);
        assertEquals(0, query.size());
        assertTrue(query.isEmpty());
        verify(daoAccount).getAll();

    }


    @Test
    public void getAllTest1(){
        AccountService accountService = new AccountService(daoAccount);

        ArrayList<Account> accounts =  new ArrayList<Account>();
        for(int i = 0 ;i < 10;i++){
            accounts.add(
                    new Account.AccountBuilder()
                    .setAccountID(i)
                    .setAccountName("account" + i)
                    .setClientID(i+i)
                    .setCreditCardNumber("0000-"+i+"000-0000-1111")
                    .setScore(i*100+i)
                    .setBlocked(i%3==0)
                    .build()
            );
        }
        when(daoAccount.getAll()).thenReturn(accounts);

        ArrayList<Account> query = accountService.getAll();
        assertEquals(accounts, query);
        assertEquals(10, query.size());
        verify(daoAccount).getAll();

    }

    @Test
    public void getClientsAccountTest0(){
        AccountService accountService = new AccountService(daoAccount);

        ArrayList<Account> accounts =  new ArrayList<Account>();
        for(int i = 0 ;i < 10;i++){
            accounts.add(
                    new Account.AccountBuilder()
                            .setAccountID(i)
                            .setAccountName("account" + i)
                            .setClientID(1)
                            .setCreditCardNumber("0000-"+i+"000-0000-1111")
                            .setScore(i*100+i)
                            .setBlocked(i%3==0)
                            .build()
            );
        }
        when(daoAccount.getWhere(" WHERE client_id = 1" )).thenReturn(accounts);

        ArrayList<Account> query = accountService.getClientsAccount(1);
        assertEquals(accounts, query);
        assertEquals(10, query.size());
        assertEquals(1,query.get(5).getClientID());
        verify(daoAccount).getWhere(" WHERE client_id = 1" );

    }

    @Test
    public void getAccountTest0(){
        AccountService accountService = new AccountService(daoAccount);

        Account acc =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account1")
                        .setClientID(2)
                        .setCreditCardNumber("0000-"+1+"000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();

        when(daoAccount.getWhereOne("account1" )).thenReturn(acc);

        Account res = accountService.getAccount("account1");
        assertEquals(acc, res);

        verify(daoAccount).getWhereOne("account1" );

    }

    @Test
    public void setBlockedTest0(){
        AccountService accountService = new AccountService(daoAccount);

        Account acc =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account1")
                        .setClientID(2)
                        .setCreditCardNumber("0000-"+1+"000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();

        when(daoAccount.getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 2")).thenReturn(acc);

        accountService.setBlocked(1,2,false);

        assertEquals(true, acc.getBlocked());

        verify(daoAccount).getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 2");

    }

    @Test(expected = RuntimeException.class)
    public void setBlockedTest1() {
        AccountService accountService = new AccountService(daoAccount);

        Account acc =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account1")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(true)
                        .build();

        when(daoAccount.getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 2")).thenReturn(acc);

        accountService.setBlocked(1, 2, false);

        verify(daoAccount).getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 2");
    }
    @Test
    public void setBlockedTest2() {
        AccountService accountService = new AccountService(daoAccount);

        Account acc =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account1")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();

        when(daoAccount.getWhereOne(" WHERE ACCOUNT_ID = 1")).thenReturn(acc);

        accountService.setBlocked(1, 0, true);

        verify(daoAccount).getWhereOne(" WHERE ACCOUNT_ID = 1");
    }


    @Test(expected = RuntimeException.class)
    public void setUnblockedTest0() {
        AccountService accountService = new AccountService(daoAccount);

        Account acc =
                new Account.AccountBuilder()
                        .setAccountID(1)
                        .setAccountName("account1")
                        .setClientID(2)
                        .setCreditCardNumber("0000-" + 1 + "000-0000-1111")
                        .setScore(1000)
                        .setBlocked(false)
                        .build();

        when(daoAccount.getWhereOne(" WHERE ACCOUNT_ID = 1")).thenReturn(acc);

        accountService.setBlocked(1, 0, false);

        verify(daoAccount).getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 2");
    }

    @Test(expected = RuntimeException.class)
    public void setUnblockedTest1() {
        AccountService accountService = new AccountService(daoAccount);


        accountService.setUnblocked(1, 0, false);

        verify(daoAccount).getWhereOne(" WHERE ACCOUNT_ID = 1 AND CLIENT_ID = 0");
    }

    @Test
    public void sortTest0(){
        AccountService accountService = new AccountService(daoAccount);

        ArrayList<Account> arr =  new ArrayList<Account>();
        for(int i = 0 ;i < 10;i++){
            arr.add(
                    new Account.AccountBuilder()
                            .setAccountID(i)
                            .setAccountName("account" + i)
                            .setClientID(i+i)
                            .setCreditCardNumber("0000-"+i+"000-0000-1111")
                            .setScore(i*100+i)
                            .setBlocked(i%3==0)
                            .build()
            );
        }

        when(daoAccount.getAll()).thenReturn(arr);
        ArrayList<Account> query = accountService.getAll();

        accountService.sort(query,1);

        arr.sort(Comparator.comparing(Account::getAccountName));

        assertEquals(arr.get(3).getAccountID(), query.get(3).getAccountID());
        assertEquals(10, query.size());


        accountService.sort(query,2);
        arr.sort(Comparator.comparing(Account::getAccountID));
        assertEquals(arr.get(6).getAccountID(), query.get(6).getAccountID());
        assertEquals(10, query.size());

        accountService.sort(query,3);
        arr.sort(Comparator.comparing(Account::getScore));
        assertEquals(arr.get(9).getAccountID(), query.get(9).getAccountID());
        assertEquals(10, query.size());

        verify(daoAccount).getAll();

    }
}
