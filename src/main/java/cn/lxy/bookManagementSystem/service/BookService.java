package cn.lxy.bookManagementSystem.service;

import cn.lxy.bookManagementSystem.dao.BookDAO;
import cn.lxy.bookManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;

    public List<Book> getAllBooks() {
        try {
            return bookDAO.selectAll();
        } catch (Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public Book getBook(int id) {
        try {
            return bookDAO.selectBookById(id);
        } catch(Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public Book getBook(String name) {
        try {
            return bookDAO.selectBookByName(name);
        } catch(Exception e) {
//            logger.error(e);
            return null;
        }
    }
}
