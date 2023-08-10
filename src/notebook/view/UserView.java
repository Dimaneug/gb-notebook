package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String userId;
            String firstName;
            String lastName;
            String phone;
            User u;
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case NONE -> {
                    System.out.println("What are you looking for? :D");
                }
                case LIST -> {
                    List<User> users = userController.readAll();
                    System.out.println(users);
                }
                case CREATE -> {
                    firstName = prompt("Имя: ");
                    lastName = prompt("Фамилия: ");
                    phone = prompt("Номер телефона: ");
                    u = userController.createUser(firstName, lastName, phone);
                    userController.saveUser(u);
                }
                case READ -> {
                    userId = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userId));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case UPDATE -> {
                    userId = prompt("Enter user id: ");
                    firstName = prompt("Имя: ");
                    lastName = prompt("Фамилия: ");
                    phone = prompt("Номер телефона: ");
                    u = userController.createUser(firstName, lastName, phone);
                    userController.updateUser(userId, u);
                }
                case DELETE -> {
                    userId = prompt("Enter user id: ");
                    if (!userController.deleteUser(Long.parseLong(userId))) {
                        System.out.println("User not found");
                    }
                }
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
