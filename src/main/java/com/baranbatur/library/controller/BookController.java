package com.baranbatur.library.controller;

import com.baranbatur.library.dto.BookDto;
import com.baranbatur.library.service.abstracts.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final IBookService bookService;

    @Autowired
    BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/add")
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.updateBook(bookDto, id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

}
