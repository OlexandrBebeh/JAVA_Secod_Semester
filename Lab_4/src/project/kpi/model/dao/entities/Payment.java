package project.kpi.model.dao.entities;

import java.sql.Date;

public class Payment {

    private int paymentID;
    private double score;
    private int accountID;
    private String recipientAccount;
    private String paymentState = "PREPARED";
    private Date paymentDate;

    public Payment(){

    }
    public Payment(PaymentBuilder paymentBuilder){
        this.paymentID = paymentBuilder.paymentID;
        this.accountID = paymentBuilder.accountID;
        this.score = paymentBuilder.score;
        this.recipientAccount = paymentBuilder.recipientAccount;
        this.paymentState = paymentBuilder.paymentState;
        this.paymentDate = paymentBuilder.paymentDate;
    }
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


    public static class PaymentBuilder{
        private int paymentID;
        private int accountID;
        private double score;
        private String recipientAccount;
        private String paymentState;
        private Date paymentDate;

        public PaymentBuilder setPaymentID(int paymentID) {
            this.paymentID = paymentID;
            return this;
        }

        public PaymentBuilder setScore(double score) {
            this.score = score;
            return this;
        }

        public PaymentBuilder setAccountID(int accountID) {
            this.accountID = accountID;
            return this;
        }

        public PaymentBuilder setRecipientAccount(String recipientAccount) {
            this.recipientAccount = recipientAccount;
            return this;
        }

        public PaymentBuilder setPaymentState(String paymentState) {
            this.paymentState = paymentState;
            return this;
        }

        public PaymentBuilder setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }
        public Payment build(){
            Payment payment = new Payment(this);
            return payment;
        }
    }
}
