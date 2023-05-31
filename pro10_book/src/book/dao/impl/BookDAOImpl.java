package book.dao.impl;

import book.dao.BookDAO;
import book.pojo.Book;
import myssm.BaseDao.BaseDAO;

import java.util.List;

/**
 * @create 2023- 05- 25- 20:19
 * @desc
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book where bookStatus=0");
    }

    @Override
    public Book getBook(Integer id) {
        return load("select * from t_book where id = ? ",id);
    }
}
