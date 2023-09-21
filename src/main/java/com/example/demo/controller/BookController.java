package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
    private  final BookService bookService;
    @GetMapping("")
    public List<Book> getBook(){
        return bookService.list();
    }

    @GetMapping("{id}")
    public Book getBookById(){
        return bookService.get(1);
    }

    @PostMapping("")
    public String createBook(@RequestBody Book book){
        return bookService.saveOrUpdate(book);
    }

    @PutMapping("{id}")
    public String updateBook(@RequestBody Book book, @RequestParam Integer id){
        book.setId(id);
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping("{id}")
    public String deleteBook(@RequestParam Integer id){
        bookService.delete(id);
        return "success";
    }

}
