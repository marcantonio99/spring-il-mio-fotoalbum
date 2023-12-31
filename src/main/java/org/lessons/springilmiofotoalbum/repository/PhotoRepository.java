package org.lessons.springilmiofotoalbum.repository;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByTitle(String title);
    List<Photo> findAllByTitleContainingIgnoreCase(String title);
}
