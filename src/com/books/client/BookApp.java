package com.books.client;

import com.books.dao.BookDAO;
import com.books.model.Book;

import java.util.List;

public class BookApp {
    private static BookDAO bookDAO = new BookDAO();

    public static void main (String [] args) {
        // List all books
        System.err.println("Listing all Books: ");
        findAllBooks();

        System.out.println();
        // search book by keyword
        System.err.println("Search book by keyword in book title : Groovy:");
        searchBooks("Groovy");
        System.out.println();
        System.err.println("Search book by keyword in author's name");
        searchBooks("Josh");
    }

    private static void findAllBooks() {
        List<Book> books = bookDAO.findAllBooks();
        for (Book book: books) {
            System.out.println(book);
        }
    }

    private static void searchBooks(String keyWord) {
        List<Book> books = bookDAO.searchBooksByKeyWord(keyWord);

        for (Book book: books) {
            System.out.println(book);
        }
    }
}
