package com.alina.mylibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(length = 50)
    @NotNull
    private String bookTitle;

    @Column
    private int bookYear;

    @Column
    private int numberOfPages;

    @Column
    private int numberofVolumes;

    @Column(length = 20)
    private String bookLanguage;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String bookDescription;

    @Column(length = 60)
    private String bookDimension;

    @Column
    private float bookWeight;

    @Column
    @NotNull
    private float bookPrice;

    @Column
    @NotNull
    private String coverType;

    //TODO poate de adaugat sale pt eventuala eticheta

    @Column
    private int numberOfReviews;

    @Column
    private float bookRating;

    @Column
    @NotNull
    private int numberOfBoooks;

    @Column
    @Lob
    private Blob bookImage;



    public static Book get_copy(Book old_book)
    {
        Book new_book = new Book();
        new_book.setPublisher(old_book.getPublisher());
        new_book.setBookAuthor(old_book.getBookAuthor());
        new_book.setBookDescription(old_book.getBookDescription());
        new_book.setNumberofVolumes(old_book.getNumberofVolumes());
        new_book.setNumberOfPages(old_book.getNumberOfPages());
        new_book.setNumberOfReviews(old_book.getNumberOfReviews());
        new_book.setCoverType(old_book.getCoverType());
        new_book.setNumberOfBoooks(old_book.getNumberOfBoooks());
        new_book.setBookYear(old_book.getBookYear());
        new_book.setBookWeight(old_book.getBookWeight());
        new_book.setBookTitle(old_book.getBookTitle());
        new_book.setBookRating(old_book.getBookRating());
        new_book.setBookPrice(old_book.getBookPrice());
        new_book.setBookLanguage(old_book.getBookLanguage());
        new_book.setBookDimension(old_book.getBookDimension());
        new_book.setBookId(old_book.getBookId());
        new_book.setBooksCategories(old_book.getBooksCategories());
        new_book.setItems(old_book.getItems());
        new_book.setPersBooks(old_book.getPersBooks());
        new_book.setWishBooks(old_book.getWishBooks());
        return new_book;
        //todo de adaugat campurile noi sau de schimbat cu seterele
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getNumberofVolumes() {
        return numberofVolumes;
    }

    public void setNumberofVolumes(int numberofVolumes) {
        this.numberofVolumes = numberofVolumes;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookDimension() {
        return bookDimension;
    }

    public void setBookDimension(String bookDimension) {
        this.bookDimension = bookDimension;
    }

    public float getBookWeight() {
        return bookWeight;
    }

    public void setBookWeight(float bookWeight) {
        this.bookWeight = bookWeight;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public float getBookRating() {
        return bookRating;
    }

    public void setBookRating(float bookRating) {
        this.bookRating = bookRating;
    }

    public int getNumberOfBoooks() {
        return numberOfBoooks;
    }

    public void setNumberOfBoooks(int numberOfBoooks) {
        this.numberOfBoooks = numberOfBoooks;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<PersonalBook> getPersBooks() {
        return persBooks;
    }

    public void setPersBooks(List<PersonalBook> persBooks) {
        this.persBooks = persBooks;
    }

    public List<Wishlist> getWishBooks() {
        return wishBooks;
    }

    public void setWishBooks(List<Wishlist> wishBooks) {
        this.wishBooks = wishBooks;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<BooksCategories> getBooksCategories() {
        return booksCategories;
    }

    public void setBooksCategories(List<BooksCategories> booksCategories) {
        this.booksCategories = booksCategories;
    }

    public List<BooksAuthors> getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(List<BooksAuthors> bookAuthor) {
        this.bookAuthor = bookAuthor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                bookYear == book.bookYear &&
                numberOfPages == book.numberOfPages &&
                numberofVolumes == book.numberofVolumes &&
                Float.compare(book.bookWeight, bookWeight) == 0 &&
                Float.compare(book.bookPrice, bookPrice) == 0 &&
                numberOfReviews == book.numberOfReviews &&
                Float.compare(book.bookRating, bookRating) == 0 &&
                numberOfBoooks == book.numberOfBoooks &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(bookLanguage, book.bookLanguage) &&
                Objects.equals(bookDescription, book.bookDescription) &&
                Objects.equals(bookDimension, book.bookDimension) &&
                Objects.equals(coverType, book.coverType) &&
                Objects.equals(bookImage, book.bookImage) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(persBooks, book.persBooks) &&
                Objects.equals(wishBooks, book.wishBooks) &&
                Objects.equals(items, book.items) &&
                Objects.equals(booksCategories, book.booksCategories) &&
                Objects.equals(bookAuthor, book.bookAuthor) &&
                Objects.equals(reviews, book.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, bookYear, numberOfPages, numberofVolumes, bookLanguage, bookDescription, bookDimension, bookWeight, bookPrice, coverType, numberOfReviews, bookRating, numberOfBoooks, bookImage, publisher, persBooks, wishBooks, items, booksCategories, bookAuthor, reviews);
    }

    public Blob getBookImage() {
        return bookImage;
    }

    public void setBookImage(Blob bookImage) {
        this.bookImage = bookImage;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name = "Fk_publisher_id"))
    private Publisher publisher;


    @OneToMany(mappedBy = "book",
    cascade = CascadeType.ALL)
    private List<PersonalBook> persBooks;

    @OneToMany(mappedBy = "bookwishlist",
    cascade = CascadeType.ALL)
    private List<Wishlist> wishBooks;

    @OneToMany(mappedBy = "booksorder",
    cascade = CascadeType.ALL)
    private List<OrderItem> items;


    @OneToMany(mappedBy = "booksC",
    cascade = CascadeType.ALL)
    private List<BooksCategories> booksCategories;


    @OneToMany(mappedBy = "bookId",
            fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<BooksAuthors> bookAuthor;

    @OneToMany(mappedBy = "bookR")
    private List<Review> reviews;
}

