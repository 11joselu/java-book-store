package com.books.dao;

import com.books.model.Author;
import com.books.model.Book;
import com.books.model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO implements BookDAOImpl {

    /*WTF?*/
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {

        }
    }

    /**
     * This interface allows you to run SQL statements against
     * the database.
     *
     * @return a driver-implemented java.sql.Connection interface.
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "", "");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    public List<Book> findAllBooks() {

        List<Book> result = new ArrayList<>();
        List<Author> authorList = new ArrayList<>();

        String sql = "SELECT * FROM BOOK INNER JOIN AUTHOR ON BOOK.ID = AUTHOR.BOOK_ID";

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                Author author = new Author();

                book.setId(resultSet.getLong("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setCategoryId(resultSet.getLong("category_id"));

                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                authorList.add(author);

                book.setAuthors(authorList);
                book.setPublisherName(resultSet.getString("publisher"));

                result.add(book);

            }

        } catch(SQLException ex) {

            ex.printStackTrace();

        } finally {

            closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Book> searchBooksByKeyWord(String keyWord) {
        List<Book> result = new ArrayList<>();
        List<Author> authorList = new ArrayList<>();

        String sql = "SELECT * FROM BOOK INNER JOIN AUTHOR ON BOOK.ID = AUTHOR.ID" +
                " WHERE BOOK_TITLE LIKE '%" +
                keyWord.trim() +
                "%'" +
                " OR FIRST_NAME LIKE '%" +
                keyWord.trim() +
                "%'" +
                " OR LAST_NAME LIKE '%" +
                keyWord.trim() +
                "%'";

        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                Author author = new Author();

                book.setId(resultSet.getLong("id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPublisherName(resultSet.getString("publisher"));

                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setBookId(resultSet.getLong("book_id"));
                authorList.add(author);

                book.setAuthors(authorList);
                result.add(book);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> result = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORY";

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setCategoryDescription(resultSet.getString("category_id"));
                result.add(category);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }
}
