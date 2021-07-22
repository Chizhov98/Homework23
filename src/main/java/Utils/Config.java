package Utils;

import UserInterface.MenuNavigator.Console.ConsoleNavigator;
import UserInterface.MenuNavigator.MenuNavigator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private MenuNavigator navigator = new ConsoleNavigator();
}
