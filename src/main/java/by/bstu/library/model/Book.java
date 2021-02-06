package by.bstu.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private Genre genre;
    private LocalDate published;
    private double price;



    public Book() {
    }

    public Book(String name, String author, Genre genre, LocalDate published, double price) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.published = published;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                author.equals(book.author) &&
                genre == book.genre &&
                published.equals(book.published) &&
                name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, genre, price, published, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", published=" + published +
                ", price=" + price +
                '}';
    }


}
