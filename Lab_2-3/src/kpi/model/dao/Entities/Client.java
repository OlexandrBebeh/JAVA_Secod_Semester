package kpi.model.dao.Entities;

public class Client{
    private int clientID;
    private String clientName;
    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private Boolean blocked;

    Client(){

    }
    Client(ClientBuilder clientBuilder){
        this.clientID = clientBuilder.clientID;
        this.clientName = clientBuilder.clientName;
        this.firstName = clientBuilder.firstName;
        this.secondName = clientBuilder.secondName;
        this.password = clientBuilder.password;
        this.email = clientBuilder.email;
        this.blocked =clientBuilder.blocked;
    }
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public static class ClientBuilder{
        private int clientID;
        private String clientName;
        private String firstName;
        private String secondName;
        private String password;
        private String email;
        private Boolean blocked;

        public ClientBuilder setClientID(int clientID) {
            this.clientID = clientID;
            return this;
        }

        public ClientBuilder setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public ClientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ClientBuilder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public ClientBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setBlocked(Boolean blocked) {
            this.blocked = blocked;
            return this;
        }
        public Client build(){
            return  new Client(this);
        }
    }
}
