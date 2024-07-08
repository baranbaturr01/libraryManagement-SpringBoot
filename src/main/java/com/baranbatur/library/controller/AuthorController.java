package com.baranbatur.library.controller;

import com.baranbatur.library.dto.AuthorDto;
import com.baranbatur.library.service.abstracts.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final IAuthorService authorService;

    @Autowired
    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping("/add")
    ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.createAuthor(authorDto));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<AuthorDto> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthor(authorDto, id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}
