package Commands;

import BasicClasses.Organization;
import BasicClasses.Worker;
import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.SerializedCommands.SerializedMessage;
import Utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class CommandReceiver {
    private final Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(CommandReceiver.class);
    private CollectionManager collectionManager = CollectionManager.getCollectionManager();

    public CommandReceiver(Socket socket) {
        this.socket = socket;
    }

    public void add(Object o) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Worker worker = (Worker) o;

        if (Validator.validateWorker(worker)) {
            collectionManager.add(worker);
            out.writeObject(new SerializedMessage("Элемент добавлен в коллекцию."));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды ADD", socket.getInetAddress(), socket.getPort()));
    }

    public void removeById(String ID) throws IOException {
        Integer workerId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            workerId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(workerId)) {
                collectionManager.removeById(workerId);
                out.writeObject(new SerializedMessage("Элемент с ID " + workerId + " успешно удален из коллекции."));
            } else { out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды REMOVE_BY_ID", socket.getInetAddress(), socket.getPort()));
    }

    public void info() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(collectionManager.info()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды INFO", socket.getInetAddress(), socket.getPort()));
    }

    public void show() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(collectionManager.show()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды SHOW", socket.getInetAddress(), socket.getPort()));
    }

    public void update(String ID, Worker worker) throws IOException {
        Integer workerId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            workerId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(workerId)) {
                if (Validator.validateWorker(worker)) {
                    collectionManager.update(worker, workerId);
                    out.writeObject(new SerializedMessage("Команда update выполнена."));
                } else {
                    out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
                }
            }
            else {out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды UPDATE", socket.getInetAddress(), socket.getPort()));
    }


    public void clear() throws IOException {
        collectionManager.clear();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage("Коллекция успешно очищена."));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды CLEAR", socket.getInetAddress(), socket.getPort()));
    }

    public void removeFirst() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(new SerializedMessage(collectionManager.removeFirst()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды RemoveFirst", socket.getInetAddress(), socket.getPort()));
    }

    public void removeGreater(Worker worker) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        if (Validator.validateWorker(worker)) {
            out.writeObject(new SerializedMessage(collectionManager.removeGreater(worker)));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды REMOVE_GREATER", socket.getInetAddress(), socket.getPort()));
    }


    public void addIfMax(Object o) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Worker worker = (Worker) o;

        if (Validator.validateWorker(worker)) {
            collectionManager.addIfMax(worker);
            out.writeObject(new SerializedMessage(collectionManager.addIfMax(worker)));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды AddIfMax", socket.getInetAddress(), socket.getPort()));
    }

    public void minByName() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(new SerializedMessage(collectionManager.minByName()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды MinByName", socket.getInetAddress(), socket.getPort()));
    }

    public void sumOfSalary() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(new SerializedMessage(collectionManager.sumOfSalary()));
        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды SumOfSalary", socket.getInetAddress(), socket.getPort()));
    }

    public void count_greater_than_organization(Organization organizationToCompare) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        if (Validator.validateOrganization(organizationToCompare)) {
            out.writeObject(new SerializedMessage(collectionManager.count_greater_than_organization(organizationToCompare)));
        } else {
            out.writeObject(new SerializedMessage("Полученный элемент не прошел валидацию на стороне сервера."));
        }

        logger.info(String.format("Клиенту %s:%s отправлен результат работы команды count_greater_than_organization", socket.getInetAddress(), socket.getPort()));
    }
}
