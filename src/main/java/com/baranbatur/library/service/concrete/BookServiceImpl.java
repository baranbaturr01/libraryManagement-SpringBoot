package com.baranbatur.library.service.concrete;

import com.baranbatur.library.dto.AuthorDto;
import com.baranbatur.library.dto.BookDto;
import com.baranbatur.library.exception.NotFoundException;
import com.baranbatur.library.model.Author;
import com.baranbatur.library.model.Book;
import com.baranbatur.library.repository.BookRepository;
import com.baranbatur.library.service.abstracts.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    @Autowired
    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {

        return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BookDto getBookById(Integer id) {
        return bookRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new NotFoundException("Book Not Found"));
    }

    @Transactional
    public BookDto createBook(BookDto bookDto) {

        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDto updateBook(BookDto bookDto, Integer id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found"));
        book.setName(bookDto.getName());
        book.setAuthors(bookDto.getAuthors().stream().map(this::convertToAuthorEntity).collect(Collectors.toSet()));
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    public void deleteBook(Integer id) {

        if (!bookRepository.existsById(id)) throw new NotFoundException("Book Not Found");
        bookRepository.deleteById(id);
    }

    public BookDto convertToDTO(Book book) {

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthors(book.getAuthors().stream().map(this::convertToAuthorDTO).collect(Collectors.toSet()));
        return bookDto;
    }

    public Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthors(bookDto.getAuthors().stream().map(this::convertToAuthorEntity).collect(Collectors.toSet()));
        return book;
    }

    public AuthorDto convertToAuthorDTO(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }

    public Author convertToAuthorEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        return author;
    }
}
