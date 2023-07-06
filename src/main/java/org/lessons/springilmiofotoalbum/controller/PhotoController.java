package org.lessons.springilmiofotoalbum.controller;

import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String list(Model model){
        List<Photo> photos = photoRepository.findAll();

        model.addAttribute("photoList", photos);

        return "/photos/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer photoId, Model model){

        Optional<Photo> result = photoRepository.findById(photoId);
        if (result.isPresent()){
            model.addAttribute("photo", result.get());

            return "/photos/detail";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
