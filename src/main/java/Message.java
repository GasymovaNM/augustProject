public class Message {
    private String userName;
    private String message;

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return userName + ": " + message;
    }
}
