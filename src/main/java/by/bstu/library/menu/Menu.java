package by.bstu.library.menu;

import by.bstu.library.model.Book;
import by.bstu.library.repository.BookRepository;
import by.bstu.library.model.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public BookRepository bookRepository;
    public static final Scanner scanner = new Scanner(System.in);


    public Menu(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void runMenu() {
        int choice = 0;
        while (choice != 6) {
            dispayMainMenu();
            choice = getUserChoice();
            handleUserChoice(choice);
        }

    }

    public void dispayMainMenu() {
        System.out.println("1.Add new book");
        System.out.println("2.Edit book");
        System.out.println("3.Show your library");
        System.out.println("4.Delete any book");
        System.out.println("5.Search");
        System.out.println("6.Exit");
    }

    public Book fillBookData() {
        Book book = new Book();
        System.out.println("Enter book name: ");
        scanner.nextLine();
        book.setName(scanner.nextLine());
        System.out.println("Enter author name: ");
        book.setAuthor(scanner.nextLine());
        System.out.println("Enter book price: ");
        book.setPrice(scanner.nextDouble());
        System.out.println("Enter published date: ");
        scanner.nextLine();
        String stringDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(stringDate);
        book.setPublished(date);
        System.out.println("Enter book genre: ");
        String stringGenre = scanner.nextLine();
        Genre genre = Genre.valueOf(stringGenre.toUpperCase());
        book.setGenre(genre);
        return book;
    }

    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                Book book = fillBookData();
                bookRepository.addBook(book);
                break;
            case 2:
                List<Book> searchedListToEdit = searchBook();
                for (int i = 0; i < searchedListToEdit.size() ; i++) {
                    System.out.println(i+1 + ". " + searchedListToEdit.get(i));
                }
                System.out.println("Choose book what you want to edit: ");
                int editId = scanner.nextInt();
                Book editBook = searchedListToEdit.get(editId-1);
                Book replacedEditBook = fillBookData();
                bookRepository.editBook(editBook.getId(), replacedEditBook);
                System.out.println("Book was edited");
                break;
            case 3:
                showAllBooks();
                break;
            case 4:
                List<Book> searchedListToDelete = searchBook();
                for (int i = 0; i < searchedListToDelete.size() ; i++) {
                    System.out.println(i+1 + ". " + searchedListToDelete.get(i));
                }
                System.out.println("Choose book what you want to delete: ");
                int deleteId = scanner.nextInt();
                Book deleteBook = searchedListToDelete.get(deleteId-1);
                bookRepository.deleteBookById(deleteBook.getId());
                System.out.println("Book deleted successfully!");
                break;
            case 5:
                List<Book> searchedList = searchBook();
                for (int i = 0; i < searchedList.size() ; i++) {
                    System.out.println(i+1 + ". " + searchedList.get(i));
                }
                break;
        }
    }

    public int getUserChoice() {
        System.out.println("Enter your choice: ");
        return scanner.nextInt();
    }

    public void showAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
            books.forEach(book -> {
            System.out.println((books.indexOf(book) + 1)+ ". " + book.toString());
        });
    }

    public List<Book> searchBook() {
        System.out.println("Please enter name book what you want to find");
        scanner.nextLine();
        String searchedName = scanner.nextLine();
        return bookRepository.findByName(searchedName);
    }

}
