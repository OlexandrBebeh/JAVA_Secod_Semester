package classes.kpi.model.services;

import classes.kpi.model.dao.AbstractDAO;
import classes.kpi.model.dao.AbstractDAOFactory;
import classes.kpi.model.dao.DAOAccount;
import classes.kpi.model.dao.entities.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class AccountService {

    DAOAccount daoAccount = null;

    public AccountService() {
        daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
    }


    public AccountService(AbstractDAO dao) {
        daoAccount = (DAOAccount)dao;
    }

    public void close(){
        daoAccount.close();
    }

    public ArrayList<Account> getAll(){
        return daoAccount.getAll();
    }

    public ArrayList<Account> getClientsAccount(int id){
        return daoAccount.getWhere(" WHERE client_id = " + id);
    }

    public ArrayList<Account> sort(ArrayList<Account> accounts, int sort){
        if(sort == 1){
            accounts.sort(Comparator.comparing(Account::getAccountName));
        } else if(sort == 2){
            accounts.sort(Comparator.comparing(Account::getAccountID));
        }else if (sort == 3){
            accounts.sort(Comparator.comparing(Account::getScore));
        }

        return accounts;
    }

    public Account getAccount(String str){
        return daoAccount.getWhereOne(str);
    }

    public ArrayList<Account> getAccounts(String str){
        return daoAccount.getWhere(str);
    }

    public void replenishAccount(int id,double score,int client_id){
        Account account = daoAccount.getWhereOne(" WHERE account_id = "+id+" and client_id = " + client_id);
        if(account == null){
            throw new RuntimeException("Account doesn't exist!");
        }
        if(account.getBlocked()){
            throw new RuntimeException("Account blocked!");
        }
        account.setScore(account.getScore() + score);

        daoAccount.update(account);
    }

    public void setBlocked(int account_id, int user_id, Boolean isAdmin){
        Account account;
        try {
        if(isAdmin){
            account = getAccount(" WHERE ACCOUNT_ID = " + account_id);
            if(account==null){
                throw new RuntimeException("Account doesn't exist!");
            }

        } else {

            account = getAccount(" WHERE ACCOUNT_ID = " + account_id + " AND CLIENT_ID = " + user_id);

            if(account==null){
                throw new RuntimeException("Account doesn't exist!");
            }
            if(account.getBlocked()){
                throw new RuntimeException("Account already blocked!");
            }

        }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        account.setBlocked(true);
        daoAccount.update(account);
    }

    public void setUnblocked(int account_id, int user_id, Boolean isAdmin){

        UnblockQueryService unblockQueryService = new UnblockQueryService();
        try {
            if (isAdmin) {
                Account account = getAccount(" WHERE ACCOUNT_ID = " + account_id);
                if (account == null) {
                    throw new RuntimeException("Account doesn't exist!");
                }
                account.setBlocked(false);

                daoAccount.update(account);
                unblockQueryService.delete(account_id);

            } else {

                Account account = getAccount(" WHERE ACCOUNT_ID = " + account_id + " AND CLIENT_ID = " + user_id);

                if (!account.getBlocked()) {

                    throw new RuntimeException("Account already unblocked!");
                }
                try {
                    unblockQueryService.insert(account_id);
                } catch (Exception e) {
                    throw new RuntimeException("Account already in queue to unblock!");
                }
            }
        }catch (Exception e){
            unblockQueryService.close();
            throw new RuntimeException(e.getMessage());
        }
        unblockQueryService.close();
    }

    public ArrayList<Account> getBlocked(){

        UnblockQueryService unblockQueryService = new UnblockQueryService();


        ArrayList<Integer> integers= unblockQueryService.getAll();

        unblockQueryService.close();

        if(Objects.requireNonNull(integers).size()!=0){
        StringBuilder str = new StringBuilder();
        for (Integer i: integers) {
            str.append( i);
            str.append(",");
        }

        String req = " WHERE account_id in (" + str.substring(0,str.length()-1) +") ";

        return getAccounts(req);
        } else return null;
    }
}
