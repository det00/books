/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.det00.books.repositories;

import com.det00.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author estev
 */
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
