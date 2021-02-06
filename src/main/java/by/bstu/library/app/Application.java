package by.bstu.library.app;

import by.bstu.library.menu.Menu;
import by.bstu.library.repository.BookDatabaseRepository;

public class Application {
    public static void main(String[] args) {
        try {
            Menu menu = new Menu(new BookDatabaseRepository());
            menu.runMenu();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}