package kpi.model.services;

import kpi.model.dao.AbstractDAOFactory;
import kpi.model.dao.DAOAccount;
import kpi.model.dao.Entities.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class AccountService {
    DAOAccount daoAccount = (DAOAccount) AbstractDAOFactory.getDAO("accounts");
    
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

    public void setBlocked(int id, UserHolder userHolder){
        if(userHolder.getUserEnum()==Users.ADMIN){
            Account account = getAccount(" WHERE ACCOUNT_ID = " + id);
            if(account==null){
                throw new RuntimeException("Account doesn't exist!");
            }
            account.setBlocked(true);

            daoAccount.update(account);
        } else {

            Account account = getAccount(" WHERE ACCOUNT_ID = " + id + " AND CLIENT_ID = " + userHolder.getId());

            if(account==null){
                throw new RuntimeException("Account doesn't exist!");
            }
            if(account.getBlocked()){
                throw new RuntimeException("Account already blocked!");
            }
            account.setBlocked(true);

            daoAccount.update(account);
        }
    }

    public void setUnblocked(int id, UserHolder userHolder){
        UnblockQueryService unblockQueryService = new UnblockQueryService();

        if(userHolder.getUserEnum()==Users.ADMIN){
            Account account = getAccount(" WHERE ACCOUNT_ID = " + id);
            if(account==null){
                throw new RuntimeException("Account doesn't exist!");
            }
            account.setBlocked(false);

            daoAccount.update(account);
            unblockQueryService.delete(id);

        } else {

            Account account = getAccount(" WHERE ACCOUNT_ID = " + id + " AND CLIENT_ID = " + userHolder.getId());

            if (!account.getBlocked()) {
                throw new RuntimeException("Account already unblocked!");
            }

            unblockQueryService.insert(id);
        }
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
