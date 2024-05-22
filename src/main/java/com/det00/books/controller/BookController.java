/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.det00.books.controller;

import com.det00.books.models.Book;
import com.det00.books.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author estev
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
     
    @Autowired
    private BookRepository bookRepository;
    
    @CrossOrigin
    @GetMapping
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else 
            return ResponseEntity.ok(book.get());
    }
    
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Book> createBook (@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook (@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        else{
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        
    }
    
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook (@PathVariable Long id, @RequestBody Book updatedBook){
        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        else {
            updatedBook.setId(id);
            bookRepository.save(updatedBook);
            return ResponseEntity.ok(updatedBook);
        }
    }
    @GetMapping("/vote/{id}/{rating}")
    public ResponseEntity<Book> updateRating (@PathVariable Long id, @PathVariable double rating){
        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Book> opt = bookRepository.findById(id);
        Book book = opt.get();
        book.setVotes(book.getVotes()+1);
        book.setRating((book.getVotes() * book.getRating()) / book.getVotes());
        
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }
}
