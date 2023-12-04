package pairmatching.controller;

import java.util.List;
import pairmatching.domain.Command;
import pairmatching.view.CommandInputView;

public class MainController {
    private final CommandInputView commandInputView;
    private final List<SubController> subControllers;

    public MainController(CommandInputView commandInputView, List<SubController> subControllers) {
        this.commandInputView = commandInputView;
        this.subControllers = subControllers;
    }

    public void run() {
        while (true) {
            try {
                Command command = commandInputView.getCommand();
                if (command.isQuit()) {
                    break;
                }
                SubController subController = getSubController(command);
                subController.process();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private SubController getSubController(Command command) {
        return subControllers.stream()
                .filter(subController -> subController.isAcceptable(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 메뉴를 선택해주세요."));
    }
}
