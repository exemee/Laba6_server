package Commands.ConcreteCommands;

import BasicClasses.Worker;
import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

public class RemoveById extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        String arg = argObject.toString();
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver(socket);
            commandReceiver.removeById(arg);
        }
    }
}
