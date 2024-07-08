package com.baranbatur.library.service.abstracts;

import com.baranbatur.library.dto.AuthorDto;
import com.baranbatur.library.dto.BookDto;
import com.baranbatur.library.model.Author;
import com.baranbatur.library.model.Book;

import java.util.List;

public interface IAuthorService {

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(Integer id);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(AuthorDto authorDto, Integer id);

    void deleteAuthor(Integer id);

    AuthorDto convertToDTO(Author author);

    Author convertToEntity(AuthorDto authorDto);

    BookDto convertBookToDTO(Book book);

    Book convertBookToEntity(BookDto bookDto);


}
