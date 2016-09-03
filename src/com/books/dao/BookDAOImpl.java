package com.books.dao;

import java.util.List;
import com.books.model.Book;
import com.books.model.Category;

public interface BookDAOImpl {

    /** Listing all the books from the database */
    public List<Book> findAllBooks();

    /** Allow to search books by keyword in the title of the book or by the first and last name of the author */
    public List<Book> searchBooksByKeyWord(String keyWord);

    /** Is required by the application to provide categorized listing of books */
    public List<Category> findAllCategories();

    public void insert(Book book);

    public void update(Book book);

    public void delete(Book book);

}
