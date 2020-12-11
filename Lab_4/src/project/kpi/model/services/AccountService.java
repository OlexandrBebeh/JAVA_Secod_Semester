package project.kpi.model.services;

import project.kpi.model.dao.AbstractDAOFactory;
import project.kpi.model.dao.DAOAccount;
import project.kpi.model.dao.entities.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class AccountService {

    
    public ArrayList<Account> getAll(){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        ArrayList<Account> accs = daoAccount.getAll();
        daoAccount.close();
        return accs;
    }

    public ArrayList<Account> getClientsAccount(int id){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        ArrayList<Account> accs = daoAccount.getWhere(" WHERE client_id = " + id);
        daoAccount.close();
        return accs;
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
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        Account acc = daoAccount.getWhereOne(str);
        daoAccount.close();
        return acc;
    }

    public ArrayList<Account> getAccounts(String str){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        ArrayList<Account> accs = daoAccount.getWhere(str);
        daoAccount.close();
        return accs;
    }

    public void replenishAccount(int id,double score,int client_id){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        Account account = daoAccount.getWhereOne(" WHERE account_id = "+id+" and client_id = " + client_id);
        if(account == null){
            throw new RuntimeException("Account doesn't exist!");
        }
        if(account.getBlocked()){
            throw new RuntimeException("Account blocked!");
        }
        account.setScore(account.getScore() + score);

        daoAccount.update(account);
        daoAccount.close();
    }

    public void setBlocked(int account_id, int user_id, Boolean isAdmin){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        Account account;
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
        account.setBlocked(true);
        daoAccount.update(account);
        daoAccount.close();
    }

    public void setUnblocked(int account_id, int user_id, Boolean isAdmin){
        DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
        UnblockQueryService unblockQueryService = new UnblockQueryService();

        if(isAdmin){
            Account account = getAccount(" WHERE ACCOUNT_ID = " + account_id);
            if(account==null){
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

            unblockQueryService.insert(account_id);
        }
        daoAccount.close();
    }

    public ArrayList<Account> getBlocked(){

        UnblockQueryService unblockQueryService = new UnblockQueryService();
        ArrayList<Integer> integers= unblockQueryService.getAll();

        if(Objects.requireNonNull(integers).size()!=0){
        StringBuilder str = new StringBuilder();
        for (Integer i: integers) {
            str.append( i);
            str.append(",");
        }

        String req = " WHERE account_id in (" + str.substring(0,str.length()-1) +") ";

        return getAccounts(req);}
        else return null;
    }
}
