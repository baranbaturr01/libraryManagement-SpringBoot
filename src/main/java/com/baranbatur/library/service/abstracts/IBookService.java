package com.baranbatur.library.service.abstracts;

import com.baranbatur.library.dto.AuthorDto;
import com.baranbatur.library.dto.BookDto;
import com.baranbatur.library.model.Author;
import com.baranbatur.library.model.Book;

import java.util.List;

public interface IBookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Integer id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto, Integer id);

    void deleteBook(Integer id);

    BookDto convertToDTO(Book book);

    Book convertToEntity(BookDto bookDto);

    AuthorDto convertToAuthorDTO(Author author);

    Author convertToAuthorEntity(AuthorDto authorDto);


}
