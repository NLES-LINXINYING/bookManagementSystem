package cn.lxy.bookManagementSystem.controllers;

import cn.lxy.bookManagementSystem.model.User;
import cn.lxy.bookManagementSystem.service.BookService;
import cn.lxy.bookManagementSystem.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {
        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadAllBooksView(model);
        return "book/books";
    }

    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
