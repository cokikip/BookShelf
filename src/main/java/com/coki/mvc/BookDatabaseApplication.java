package com.coki.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.coki.mvc.entities.User;

@SpringBootApplication
public class BookDatabaseApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(BookDatabaseApplication.class, args);
		UserRepository repo=context.getBean(UserRepository.class);
		/*BookRepository repository = context.getBean(BookRepository.class);
		Book book=new Book();
		book.setName("Think big");
		book.setPageCount(200);
		//book.setPrice(1200);
		book.setPublishedDate(new Date());
		repository.save(book);*/
		User user=new User();
		user.setFirstname("collins");
		user.setLastname("Kiplagat");
		user.setUsername("coki");
		user.setPassword("Collins");
		repo.save(user);
	}

}
