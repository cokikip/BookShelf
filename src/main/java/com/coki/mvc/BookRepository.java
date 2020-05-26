package com.coki.mvc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coki.mvc.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
