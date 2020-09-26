import ServerSocket.Controller;
import Utils.JSON.ParserJSON;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(ParserJSON::collectionToJson));
        Controller controller = new Controller();
        controller.run(args[0]);
    }
}
