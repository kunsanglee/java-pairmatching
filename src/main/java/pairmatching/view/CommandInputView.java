package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import pairmatching.domain.Command;

public class CommandInputView {

    public static final String INPUT_COMMAND = "기능을 입력하세요.";
    public static final String NUMBER_MENU = "%s. %s";

    public Command getCommand() {
        System.out.println(INPUT_COMMAND);
        Arrays.stream(Command.values())
                .forEach(controlCommand -> System.out.println(
                        String.format(NUMBER_MENU, controlCommand.getCommandValue(), controlCommand.getCommandName())));
        return Command.getCommand(Console.readLine());
    }
}
