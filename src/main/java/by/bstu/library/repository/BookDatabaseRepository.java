package by.bstu.library.repository;

import by.bstu.library.model.Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDatabaseRepository implements BookRepository {

    private static final String url = "jdbc:postgresql://localhost:5432/test";
    private static final String user = "postgres";
    private static final String password = "523";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public List<Book> getAllBooks() {
        String query = "SELECT * FROM library";
        List<Book> books = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                books.add(ResultSetBookConverter.convertToBook(rs));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return books;
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO library(name,author,genre,published,price)"
                + "VALUES(?,?,?,?,?)";
        try {
            con = DriverManager.getConnection(url, user, password);

            PreparedStatement prstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstmt.setString(1, book.getName());
            prstmt.setString(2, book.getAuthor());
            prstmt.setString(3, book.getGenre().name());
            prstmt.setDate(4, Date.valueOf(book.getPublished()));
            prstmt.setDouble(5, book.getPrice());

            prstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }

        }
    }

    @Override
    public List<Book> findByName(String name) {
        String query = "SELECT * FROM library WHERE NAME = ?";
        List<Book> searchedBooks = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    searchedBooks.add(ResultSetBookConverter.convertToBook(resultSet));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return searchedBooks;
    }

    @Override
    public void deleteBookById(int bookId) {
        String query = "DELETE FROM library WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editBook(int bookId, Book book) {
        String query = "UPDATE library SET name = ?, author = ?, genre = ?, published = ?, price = ? WHERE id= ?";
        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(6, bookId);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre().name());
            preparedStatement.setDate(4,Date.valueOf(book.getPublished()));
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}



