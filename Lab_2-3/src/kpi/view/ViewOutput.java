package kpi.view;

import kpi.model.dao.Entities.Account;
import kpi.model.dao.Entities.Client;
import kpi.model.dao.Entities.Payment;
import kpi.model.services.UserHolder;
import kpi.model.services.Users;

import java.util.ArrayList;

public class ViewOutput {

   public void printMessage(String str){
       System.out.println(str);
   }
    public void printEnterMenu() {
        System.out.println("Make choice:");
        System.out.println(" 1 - Enter");
        System.out.println(" 2 - Registration");
        System.out.println(" 3 - Exit");
    }

    public void printUserMenu(UserHolder userHolder){
       if(userHolder.getUserEnum()== Users.ADMIN){
           printAdminMenu();
       } else if(userHolder.getUserEnum()== Users.CLIENT){
           printClientMenu();
       }
    }

    public void printClientMenu() {
        System.out.println("Please, print number of command:");
        System.out.println(" 1 - Make payment.");
        System.out.println(" 2 - Replenish account.");
        System.out.println(" 3 - Show about me.");
        System.out.println(" 4 - Show accounts.");
        System.out.println(" 5 - Show payments.");
        System.out.println(" 6 - Block account.");
        System.out.println(" 7 - Unblock account.");
        System.out.println(" 8 - Exit.");

    }
    public void printAdminMenu() {
        System.out.println("Please, enter number of command:");
        System.out.println(" 1 - Block client.");
        System.out.println(" 2 - Unblock client.");
        System.out.println(" 3 - Show clients.");
        System.out.println(" 4 - Show accounts.");
        System.out.println(" 5 - Show payments.");
        System.out.println(" 6 - Block account.");
        System.out.println(" 7 - Unblock account.");
        System.out.println(" 8 - Show query to unblock.");
        System.out.println(" 9 - Exit.");
    }

    public void printAccounts(ArrayList<Account> accounts) {
        if(accounts==null){
            System.out.println("There is empty list!");
            return;
        }
        System.out.printf("%-10s | ", "AccountID");
        System.out.printf("%-20s | ", "AccountName");
        System.out.printf("%-22s | ", "Credit card number");
        System.out.printf("%-10s | ", "Score");
        System.out.print("\n");

        for (Account account : accounts) {
            System.out.printf("%-10s | ", account.getAccountID());
            System.out.printf("%-20s | ", account.getAccountName());
            System.out.printf("%-22s | ", account.getCreditCardNumber());
            System.out.printf("%-10s | ", account.getScore());
            if(account.getBlocked()) System.out.printf("%-7s ", " BLOCKED");
            System.out.print("\n");

        }
    }
    public void printPayments(ArrayList<Payment> payments) {
        if(payments==null){
            System.out.println("There is empty list!");
            return;
        }
        System.out.printf("%-9s | ", "PaymentID");
        System.out.printf("%-20s | ", "Score");
        System.out.printf("%-15s | ", "PaymentState");
        System.out.printf("%-20s | ", "RecipientAccount");
        System.out.printf("%-20s | ", "Date");
        System.out.print("\n");

        for (Payment payment : payments) {
            System.out.printf("%-9s | ", payment.getPaymentID());
            System.out.printf("%-20s | ", payment.getScore());
            System.out.printf("%-15s | ", payment.getPaymentState());
            System.out.printf("%-20s | ", payment.getRecipientAccount());
            System.out.printf("%-20s | ", payment.getPaymentDate());
            System.out.print("\n");
        }
    }
    public void printClients(ArrayList<Client> clients){
        if(clients==null){
            System.out.println("There is empty list!");
            return;
        }
        System.out.printf("%-8s | ", "ClientID");
        System.out.printf("%-20s | ", "ClientName");
        System.out.printf("%-10s | ", "FirstName");
        System.out.printf("%-20s | ", "SecondName");
        System.out.printf("%-30s | ", "Email");
        System.out.printf("%-7s | ", "Blocked");
        System.out.print("\n");

        for (Client client : clients) {
            System.out.printf("%-8s | ", client.getClientID());
            System.out.printf("%-20s | ", client.getClientName());
            System.out.printf("%-10s | ", ((client.getFirstName()==null)? "" : client.getFirstName()));
            System.out.printf("%-20s | ", ((client.getSecondName()==null)? "" : client.getSecondName()));
            System.out.printf("%-30s | ", client.getEmail());
            System.out.printf("%-7s | ", client.getBlocked());
            System.out.print("\n");
        }
    }
    public void printClient(Client client){
        if(client==null){
            System.out.println("There is empty client!");
            return;
        }
        System.out.println("Username: " + client.getClientName());
        System.out.println("Firstname: " + client.getFirstName());
        System.out.println("Secondname: " + client.getSecondName());
        System.out.println("email: " + client.getEmail());

    }


}
