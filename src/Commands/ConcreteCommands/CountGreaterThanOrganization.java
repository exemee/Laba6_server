package Commands.ConcreteCommands;

import BasicClasses.Organization;
import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

public class CountGreaterThanOrganization extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver(socket);
        commandReceiver.count_greater_than_organization((Organization) argObject);
    }
}
