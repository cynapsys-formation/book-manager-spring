package com.cyn.books.rest;

import com.cyn.books.dto.BookDto;
import com.cyn.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<BookDto> findAllBook() {
		return bookService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	BookDto findBookById(@PathVariable("id") int id) {
		return bookService.findById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	BookDto addBook(@RequestBody BookDto bookDto) {
		return bookService.add(bookDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	BookDto updateBook(@RequestBody BookDto bookDto,@PathVariable("id") int id) {
		return bookService.edit(id,bookDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Boolean deleteBookById(@PathVariable("id") int id) {
		return bookService.delete(id);
	}

}
