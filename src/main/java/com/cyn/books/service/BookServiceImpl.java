package com.cyn.books.service;

import com.cyn.books.domain.BookEntity;
import com.cyn.books.dto.BookDto;
import com.cyn.books.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
// import org.apache.log4j.Logger;

@Service
public class BookServiceImpl implements BookService {

	//final static Logger logger1 = Logger.getLogger(BookServiceImpl.class);
	final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
    BookRepository bookRepository;

	@Override
	public BookDto add(BookDto bookDto) {
		return bookEntityToBookDto((bookRepository.save(bookDtoToBookEntity(bookDto))));
	}

	@Override
	public BookDto edit(int id, BookDto bookDto) {

		try {
			BookEntity foundedBook = bookRepository.findOne(id);
			logger.info("Founded Book:" + foundedBook.toString());
			if (foundedBook != null) {
				if(bookDto.getDescription() != null) {
					foundedBook.setDescription(bookDto.getDescription());
				}
				if(bookDto.getName() != null) {
					foundedBook.setTitle(bookDto.getName());
				}
				return bookEntityToBookDto((bookRepository.save(foundedBook)));
			}
		} catch (Exception e) {
			logger.error("Technical Problem : " + e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			bookRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<BookDto> findAll() {
		return listBookEntityToListBookDto(bookRepository.findAll());
	}

	@Override
	public BookDto findById(int id) {
		return bookEntityToBookDto(bookRepository.findOne(id));
	}

	BookDto bookEntityToBookDto(BookEntity bookEntity) {
		final BookDto bookDto = new BookDto();
		bookDto.setId(bookEntity.getId());
		bookDto.setName(bookEntity.getTitle());
		bookDto.setDescription(bookEntity.getDescription());
		return bookDto;
	}

	BookEntity bookDtoToBookEntity(BookDto bookDao) {
		final BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookDao.getId());
		bookEntity.setTitle(bookDao.getName());
		bookEntity.setDescription(bookDao.getDescription());
		return bookEntity;
	}

	List<BookDto> listBookEntityToListBookDto(List<BookEntity> listBookEntity) {
		final List<BookDto> listBookDto = new ArrayList<BookDto>();

		// for (BookEntity bookEntity : listBookEntity) {
		// listBookDto.add(bookEntityToBookDto(bookEntity));
		// }
		listBookEntity.forEach(bookEntity -> listBookDto.add(bookEntityToBookDto(bookEntity)));
		// listBookEntity.forEach(listBookDto.add(::bookEntityToBookDto));
		// return Stream.of(listBookEntity).collect(Collectors.toList());

		return listBookDto;
	}

	List<BookEntity> listBookDtoToListBookEntity(List<BookDto> listBookDto) {
		final List<BookEntity> listBookEntity = new ArrayList<BookEntity>();
		listBookDto.forEach(bookDto -> listBookEntity.add(bookDtoToBookEntity(bookDto)));
		return listBookEntity;
	}

}
