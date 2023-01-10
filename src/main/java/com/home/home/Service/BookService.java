package com.home.home.Service;

import com.home.home.Domain.Book;
import com.home.home.Domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;


@RequiredArgsConstructor //final에 있는 변수들 자동으로 DI됨 원래는 @AUTOWIRED 해야됨
@Service //기능 정의 가능, transaction 관리
public class BookService {
    private final BookRepository bookRepository;

    @Transactional //서비스 함수 종료될때 commit할지 rollback할지 트랜잭션 관리 하겠다.
    public Book save(Book book){
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true) //select 할때마다 걸기
    public Book getOne(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id를 확인 해 주세요!!"));

        /*
        Optional<Book> res = bookRepository.findById(id);
        return res.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        가능?????
         */
    }

    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public Book update(Long id, Book book){
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("update 실패! id를 확인해주세요"));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    }// => 트랜잭션 종료 => DB로 갱신 => 더티체킹

    @Transactional
    public String delete(Long id){
        bookRepository.deleteById(id);
        return "ok";
    }
}
