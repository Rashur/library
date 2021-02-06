package by.bstu.library.repository;

import by.bstu.library.model.Book;

import java.util.List;

public interface BookRepository {
    void addBook(Book book);

    List<Book> findByName(String bookName);

    void deleteBookById(int bookId);

    void editBook(int bookId, Book book);

    List<Book> getAllBooks();


}
