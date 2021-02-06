package by.bstu.library.repository;

import by.bstu.library.model.Book;
import by.bstu.library.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetBookConverter {
    public static Book convertToBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setGenre(Genre.valueOf(rs.getString("genre")));
            book.setPublished(rs.getDate("published").toLocalDate());
            book.setPrice(rs.getDouble("price"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }
}
