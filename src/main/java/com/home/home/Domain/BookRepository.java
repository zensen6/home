package com.home.home.Domain;

import org.springframework.data.jpa.repository.JpaRepository;



//@Repository 해야 IOC 빈으로 등록이 되는데, Jparepo
public interface BookRepository extends JpaRepository<Book, Long> {

}
