package by.bstu.library.model;

public enum Genre {
    ADVENTURE("Adventure"),
    CLASSICS("Classics"),
    DETECTIVE("Detective"),
    FANTASY("Fantasy");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
