package kpi.controll;

import kpi.controll.commands.AbstractFactoryCommand;
import kpi.controll.commands.UserCommandsManager;
import kpi.model.dao.Entities.Client;
import kpi.model.services.UserHolder;
import kpi.model.services.Users;
import kpi.view.ViewInput;
import kpi.view.ViewOutput;

public class MainController {
    ViewOutput viewOutput = new ViewOutput();
    ViewInput viewInput = new ViewInput(viewOutput);
    UserHolder userHolder;

    public void run(){

        while (true) {

            viewOutput.printEnterMenu();
            int command = viewInput.getNumberOfCommand(1, 3);

            if(command == 1){
                new EnterControl().run();
            } else if(command == 2){
                new RegistrationControl().run();
            } else{
                System.exit(0);
            }

            try {
                UserCommandsManager userCommands = AbstractFactoryCommand.getCommand(userHolder.getUserEnum());
            while (true) {

                viewOutput.printUserMenu(userHolder);

                command = viewInput.getNumberOfCommand(1,9);
                userCommands.setCommand(command);

                 try {
                userCommands.execute(viewOutput,userHolder);
                 }catch (Exception e){
                     viewOutput.printMessage(e.getMessage());
                 }

                if(userHolder.getUserEnum()==Users.NONE) break;
            }
            }catch (Exception e){
                viewOutput.printMessage(e.getMessage());
            }
        }
    }

        public class EnterControl{
            public void run(){
                userHolder = new UserHolder();
                viewOutput.printMessage("Input your username:");

                userHolder.setUserName(viewInput.getName());

                viewOutput.printMessage("Input your password:");
                userHolder.setPassword(viewInput.getPassword());

                userHolder.userCheck();
            }
        }

        public class RegistrationControl{

            public void run(){
                userHolder = new UserHolder();
                userHolder.setUserEnum(Users.CLIENT);

                viewOutput.printMessage("Input your username:");
                userHolder.setUserName(viewInput.getName());
                viewOutput.printMessage("Input your password:");
                userHolder.setPassword(viewInput.getPassword());

                viewOutput.printMessage("Input your email:");
                userHolder.registration(new Client.ClientBuilder()
                        .setEmail(viewInput.getEmail())
                        .setFirstName(viewInput.getString("Input your First Name:"))
                        .setSecondName(viewInput.getString("Input your Second Name:"))
                        .build()
                );

                userHolder.userCheck();
            }
        }
}
