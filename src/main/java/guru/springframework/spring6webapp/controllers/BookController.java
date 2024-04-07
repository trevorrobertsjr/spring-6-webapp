package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
//    when someone access /books path, invoke this method
    @RequestMapping("/books")
    public String getBooks(Model model) {


        model.addAttribute("books", bookService.findAll());
        // this will tell Spring boot to look for a view called "books"
// .html extension will be added on automatically.
        return "books";

    }
}
