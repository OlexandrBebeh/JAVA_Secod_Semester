package kpi.controll.commands;

import kpi.model.services.Users;

public class AbstractFactoryCommand {

    public static UserCommandsManager getCommand(Users user){
        return switch (user) {
            case CLIENT -> new ClientCommandsManager();
            case ADMIN -> new AdminCommandManager();
            default -> throw new RuntimeException("Wrong name or password!");
        };
    }
}
