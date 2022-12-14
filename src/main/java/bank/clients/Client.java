package bank.clients;

public interface Client {
    void send(String destination, String toSend) throws Exception;

    String get(String destination) throws Exception;
}
