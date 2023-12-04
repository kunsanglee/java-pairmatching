package pairmatching;

import pairmatching.config.AppConfig;
import pairmatching.controller.MainController;
import pairmatching.view.CommandInputView;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(
                new CommandInputView(), AppConfig.subControllers());
        mainController.run();
    }
}
