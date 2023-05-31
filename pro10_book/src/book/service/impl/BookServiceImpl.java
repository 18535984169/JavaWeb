package book.service.impl;

import book.dao.BookDAO;
import book.pojo.Book;
import book.service.BookService;

import java.util.List;

/**
 * @create 2023- 05- 25- 20:21
 * @desc
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return  bookDAO.getBook(id);
    }
}
