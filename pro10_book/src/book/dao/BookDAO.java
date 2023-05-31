package book.dao;

import book.pojo.Book;

import java.util.List;

/**
 * @create 2023- 05- 25- 20:17
 * @desc
 */
public interface BookDAO {
    List<Book> getBookList();
    //Integer minPrice,Integer maxPrice,Integer pageNo

    Book getBook(Integer id);
}
