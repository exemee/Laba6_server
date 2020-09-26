package Commands.ConcreteCommands;

import BasicClasses.Worker;
import Commands.Command;
import Commands.CommandReceiver;
import Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.IOException;
import java.net.Socket;

public class Update extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) argObject;
        String arg =  combinedCommand.getArg();
        Worker worker = (Worker) combinedCommand.getObject();
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver(socket);
            commandReceiver.update(arg, worker);
        }
    }
}
