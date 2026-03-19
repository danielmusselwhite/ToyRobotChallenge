package toyrobot;

import toyrobot.domain.table.Table;
import toyrobot.input.CommandParser;
import toyrobot.service.RobotService;

public class Main {
    public static void main(String[] args) {
        // Create a 5x5 table as specified in brief
        Table table = new Table(5, 5);

        // Create the RobotService
        RobotService robotService = new RobotService(table);

        // Create the command parser and run console loop
        CommandParser parser = new CommandParser(robotService);
        parser.runConsole(); // infinite loop until broken by user
    }
}
