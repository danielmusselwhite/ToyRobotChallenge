package toyrobot.input;

import toyrobot.domain.robot.Direction;
import toyrobot.service.RobotService;

public class CommandParser {

    private final RobotService robotService;

    public CommandParser(RobotService robotService) {
        this.robotService = robotService;
    }

    public void runConsole() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Enter commands for the toy robot. Type EXIT to quit.");

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("EXIT")) break; // not specified in brief, but we want a way to break the loop
            executeCommand(line); // execute the command
        }
    }

    public void executeCommand(String input) {
        if (input.isBlank()) return;

        String command = input.split(" ")[0].toUpperCase();

        switch (command) {
            // more complex requires parsing of the input
            case "PLACE" -> handlePlace(input);

            // simple robot manipulation methods
            case "MOVE" -> robotService.move();
            case "LEFT" -> robotService.left();
            case "RIGHT" -> robotService.right();

            // report robots state
            case "REPORT" -> {
                String report = robotService.report();
                if (report != null) System.out.println(report);
            }

            // notify user of mistake in input
            default -> System.out.println("Invalid command: " + input);
        }
    }

    private void handlePlace(String input) {
        try {
            // parse and safety check the string
            String[] parts = input.split(" "); // should have 'PLACE X,Y,F'
            if (parts.length < 2) return; // invalid PLACE
            String[] args = parts[1].split(","); // should have X,Y,F
            if (args.length < 3) return;

            // get x,y,f
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            Direction dir = Direction.valueOf(args[2].toUpperCase());

            // place robot
            robotService.place(x, y, dir);
        } catch (Exception e) { // if robot attempted to be placed at invalid location, notify user
            System.out.println("Invalid PLACE command: " + input);
        }
    }
}