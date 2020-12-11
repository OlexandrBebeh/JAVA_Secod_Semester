package project.kpi.model.dao.entities;

public class Account {
    private int accountID;
    private int clientID;
    private String creditCardNumber;
    private String accountName;
    private double score;
    private Boolean blocked;

    Account(){
    }

    Account(AccountBuilder accountBuilder){
        this.accountID = accountBuilder.accountID;
        this.clientID = accountBuilder.clientID;
        this.creditCardNumber = accountBuilder.creditCardNumber;
        this.accountName = accountBuilder.accountName;
        this.score = accountBuilder.score;
        this.blocked = accountBuilder.blocked;
    }
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public static class AccountBuilder{
        private int accountID;
        private int clientID;
        private String creditCardNumber;
        private String accountName;
        private double score;
        private Boolean blocked;

        public AccountBuilder setAccountID(int accountID) {
            this.accountID = accountID;
            return this;
        }

        public AccountBuilder setClientID(int clientID) {
            this.clientID = clientID;
            return this;
        }

        public AccountBuilder setAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountBuilder setScore(double score) {
            this.score = score;
            return this;
        }

        public AccountBuilder setBlocked(Boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public Account build(){
            return new Account(this);
        }

        public AccountBuilder setCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }
    }
}
