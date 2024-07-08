package com.baranbatur.library.service.concrete;

import com.baranbatur.library.dto.AuthorDto;
import com.baranbatur.library.dto.BookDto;
import com.baranbatur.library.exception.NotFoundException;
import com.baranbatur.library.model.Author;
import com.baranbatur.library.model.Book;
import com.baranbatur.library.repository.AuthorRepository;
import com.baranbatur.library.service.abstracts.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        return this.authorRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public AuthorDto getAuthorById(Integer id) {
        return this.authorRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new NotFoundException("Author Not Found"));
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        Author saved = authorRepository.save(author);

        return convertToDTO(saved);
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto, Integer id) {

        Author isExistAuthor = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found"));
        isExistAuthor.setName(authorDto.getName());
        isExistAuthor.setBooks(authorDto.getBooks().stream().map(this::convertBookToEntity).collect(Collectors.toSet()));
        Author updatedAuthor = authorRepository.save(isExistAuthor);
        return convertToDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author Not Found");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDto convertToDTO(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setBooks(author.getBooks().stream().map(this::convertBookToDTO).collect(Collectors.toSet()));
        return authorDto;
    }

    @Override
    public Author convertToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBooks(authorDto.getBooks().stream().map(this::convertBookToEntity).collect(Collectors.toSet()));
        return author;
    }

    @Override
    public BookDto convertBookToDTO(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setId(book.getId());
        bookDto.setAuthors(book.getAuthors().stream().map(this::convertToDTO).collect(Collectors.toSet()));
        return bookDto;
    }

    @Override
    public Book convertBookToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthors(bookDto.getAuthors().stream().map(this::convertToEntity).collect(Collectors.toSet()));
        return book;
    }
}
