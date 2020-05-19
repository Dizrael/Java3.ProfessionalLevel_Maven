package lesson4.serializableObjectInEchoServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static lesson4.serializableObjectInEchoServer.ServerClass.SERVER_PORT;

public class ClientClass {

    private static final int CONNECTION_TIMEOUT = 10_000;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public ClientClass() throws IOException, ClassNotFoundException {
        processMessagesFromServer();
    }


    private void processMessagesFromServer() throws IOException, ClassNotFoundException {
        Socket socket = null;
        Thread thread = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", SERVER_PORT), CONNECTION_TIMEOUT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Соединение установлено!");

            thread = createInputThread();
            runOutputLoop(dataOutputStream);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ситуация 2");
            System.out.println("Соединение было закрыто!");
        } finally {
            if (thread != null) createInputThread().interrupt();
            if (socket != null) socket.close();
        }

    }

    private void runOutputLoop(DataOutputStream out) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.next();
            out.writeUTF(message);
            if (message.equals("/end")) {
                break;
            }
        }
    }

    private Thread createInputThread() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file"));
        FlatKeeper fk = (FlatKeeper) ois.readObject();
        fk.info();
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = dataInputStream.readUTF();
                    System.out.println("Server - " + message);
                    if ("/end".equals(message)) {
                        System.exit(0);
                    }
                } catch (IOException e) {
                    System.out.println("Соединение было закрыто!");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientClass clientClass = new ClientClass();
    }

}
