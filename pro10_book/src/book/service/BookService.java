package book.service;

import book.pojo.Book;

import java.util.List;

/**
 * @create 2023- 05- 25- 20:21
 * @desc
 */
public interface BookService {
    List<Book> getBookList();

    Book getBook(Integer id);
}
