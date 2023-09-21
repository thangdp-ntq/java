package com.example.demo.service;

import com.example.demo.model.Book;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookService {
    private final JdbcTemplate jdbcTemplate;

    public String saveOrUpdate(Book book) {
        if (book.getId() > 0) {
            String sql = "UPDATE book SET name=?, author=?, version=?, "
                    + "publishing_year=? , number_pages=?, is_available=? WHERE id=?";

            jdbcTemplate.update(sql, book.getName(), book.getAuthor(),
                    book.getVersion(), book.getPublishing_year(), book.getNumber_pages(), book.getIs_available(), book.getId());
        } else {
            String sql = "INSERT INTO book (name, author, version, publishing_year, number_pages, is_available)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(sql, book.getName(), book.getAuthor(),
                    book.getVersion(), book.getPublishing_year(), book.getNumber_pages(),  book.getIs_available());
        }
        return "oke";
    }

    public void delete(int contactId) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, contactId);
    }

    public Book get(int bookId) {

        String sql = "SELECT * FROM book WHERE contact_id=" + bookId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Book>() {

            @Override
            public Book extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setVersion(rs.getString("version"));
                    book.setPublishing_year(rs.getDate("publishing_year"));
                    book.setNumber_pages(rs.getInt("number_pages"));
                    book.setIs_available(rs.getBoolean("is_available"));

                    return book;
                }

                return null;
            }

        });
    }

    public List<Book> list() {
        String sql = "SELECT * FROM book";
        List<Book> contacts = jdbcTemplate.query(sql, new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setVersion(rs.getString("version"));
                book.setPublishing_year(rs.getDate("publishing_year"));
                book.setNumber_pages(rs.getInt("number_pages"));
                book.setIs_available(rs.getBoolean("is_available"));

                return book;
            }

        });

        return contacts;
    }
}
