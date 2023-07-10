package org.lessons.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String searchString, Model model){
        List<Photo> photos;

        if (searchString == null || searchString.isBlank()){
            photos = photoRepository.findAll();
        }else {
            photos = photoRepository.findAllByTitleContainingIgnoreCase(searchString);
        }

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

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("photo", new Photo());
        return "/photos/create_edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/photos/create_edit";
        }

        photoRepository.save(formPhoto);

        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Photo photo = getPhotoById(id);

        model.addAttribute("photo", photo);

        return "/photo/create_edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Photo photo = getPhotoById(id);

        formPhoto.setId(photo.getId());

        photoRepository.save(formPhoto);

        return "redirect:/photo";
    }

    private Photo getPhotoById(Integer id) {

        Optional<Photo> photoId = photoRepository.findById(id);

        if (photoId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo inesistente");
        }
        return photoId.get();
    }
}
