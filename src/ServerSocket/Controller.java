package ServerSocket;

import Collection.CollectionManager;
import Utils.CommandHandler.Decrypting;
import Utils.JSON.ParserJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Controller {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static ObjectInputStream in;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public void run(String strPort) throws IOException {
        try {
            try {
                int port = 0;
                CollectionManager collectionManager = CollectionManager.getCollectionManager();
                collectionManager.initArray();
                logger.info("Создана пустая коллекция");
                ParserJSON.fromJsonToCollection();
                try {
                    port = Integer.parseInt(strPort);
                } catch (NumberFormatException ex) {
                    logger.info("Ошибка! Неправильный формат порта.");
                    System.exit(0);
                }

                server = new ServerSocket(port);
                logger.info("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    logger.info("Ну привет, " + clientSocket);
                    try {
                        while (true) {
                            in = new ObjectInputStream(clientSocket.getInputStream());
                            Decrypting decrypting = new Decrypting(clientSocket);
                            Object o = in.readObject();
                            decrypting.decrypt(o);
                        }

                    } catch (EOFException | SocketException ex) {
                        logger.info("Клиент " + clientSocket + " отлетел");
                    } finally {
                        clientSocket.close();
                        if (in != null) { in.close(); }
                    }
                }
            } finally {
                if (clientSocket != null) { clientSocket.close(); }
                logger.info("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }
    }
}
