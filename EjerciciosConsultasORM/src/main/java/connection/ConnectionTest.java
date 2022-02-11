package connection;

public class ConnectionTest {

    public String checkConnection(){
        Connection myConnection = new Connection();
        return myConnection.getConnection()!=null  ? "connection ok": "connection error";
    }

    public static void main(String[] args) {
        ConnectionTest connectionTest = new ConnectionTest();
        System.out.println(connectionTest.checkConnection());
    }
}
