package client;

public class ClientStartup {
    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient();
        client.connect("localhost", 8090);
    }
}
