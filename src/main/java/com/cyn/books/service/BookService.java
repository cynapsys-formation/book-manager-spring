package com.cyn.books.service;

import com.cyn.books.dto.BookDto;

import java.util.List;

public interface BookService {
	BookDto add(BookDto bookDto);

	BookDto edit(int id,BookDto bookDto);

	Boolean delete(Integer id);

	List<BookDto> findAll();

	BookDto findById(int id);

}
