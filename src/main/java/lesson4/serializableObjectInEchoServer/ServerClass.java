package lesson4.serializableObjectInEchoServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerClass {

    public static final int SERVER_PORT = 8189;

    public static void main(String[] args) throws IOException {
        new ServerClass().start();
    }

    private void start() throws IOException {
        FlatKeeper fk = new FlatKeeper(69, "Johny");
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Thread thread = null;
        System.out.println("Старт эхо-сервера");
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");
            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился!");

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            thread = createInputThread(dataInputStream);
            createOutputLoop(dataOutputStream, fk);


            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Port " + SERVER_PORT + " is already used");
            e.printStackTrace();
        } finally {
            if (thread != null) thread.interrupt();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        }
    }

    private static void createOutputLoop(DataOutputStream dataOutputStream, FlatKeeper flatKeeper) throws IOException {
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file"));
        oos.writeObject(flatKeeper);
        oos.close();
        while (true) {
            String messageToClient = sc.next();
            dataOutputStream.writeUTF(messageToClient);
            if ("/end".equals(messageToClient)) {
                System.exit(0);
                break;
            }
        }
    }

    private static Thread createInputThread(DataInputStream dataInputStream) {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Object messageFromClient = dataInputStream.read();
                    if ("/end".equals(messageFromClient)) {
                        System.exit(0);
                        break;
                    }

                } catch (IOException e) {
                    System.out.println("Connection was closed");
                    break;
                }
            }

        });
        thread.start();
        return thread;
    }
}