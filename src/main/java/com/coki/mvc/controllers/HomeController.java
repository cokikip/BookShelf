package com.coki.mvc.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.coki.mvc.BookRepository;
import com.coki.mvc.entities.Book;

@Controller	
public class HomeController {
	@Autowired
	private BookRepository repo;
	@RequestMapping("/")
	public String index(Model model) throws Exception {
		ArrayList<Book> books=(ArrayList<Book>) repo.findAll();
		for (Book book : books) {
			Blob blob=book.getImage();
			int blobLength=(int)blob.length();
			byte[] b=blob.getBytes(1, blobLength);
			String baseImage=Base64.getEncoder().encodeToString(b);
			book.setBase64Image(baseImage);
			
		}
		model.addAttribute("books", books);
		return "index";
	}
	@GetMapping("/show")
	public String name(Model model) {
		ArrayList<Book> books=(ArrayList<Book>) repo.findAll();
		model.addAttribute("books", books);
		return "show";
	}
	@GetMapping("/addbook")
	public String addBook(Book book) {
		return "addbook";
	}

	@PostMapping("/addbook")
	public String saveBook(@ModelAttribute Book book) throws IOException, SerialException, SQLException {
		//getting mutipart file
		MultipartFile file=book.getFile();
		
		byte[] ibyte=file.getBytes();
		Blob blob=new SerialBlob(ibyte);
		book.setImage(blob);
		book.setPublishedDate(new Date());
		repo.save(book);
		return "redirect:/";
		
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		repo.deleteById(id);
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String updateBook(@PathVariable("id") Long id,Model model){
		Book book=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid book id:"+id));
		model.addAttribute("book", book);
		return "updatebook";
		
	}
	@PostMapping("/update/{id}")
	public String saveUpdateBook(@PathVariable Long id,@Valid Book book,BindingResult result) {
		if (result.hasErrors()) {
			book.setBookId(id);
			return "updatebook";
			
		}
		repo.save(book);
		
		return "redirect:/";
		
	}
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
		
}
