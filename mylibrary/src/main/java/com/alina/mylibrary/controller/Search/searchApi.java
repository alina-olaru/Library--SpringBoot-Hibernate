package com.alina.mylibrary.controller.Search;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/search")
@CrossOrigin
public interface searchApi {

    @GetMapping
    ApiResponse<HashSet<Book>> search(@RequestParam String query);

    @PostMapping("/filter")
    ApiResponse<HashSet<Book>> filter(@RequestParam("disponibility") Optional<Integer> disponibility,
                                      @RequestParam("minPrice") Optional<Integer> minPrice ,
                                      @RequestParam("maxPrice") Optional<Integer> maxPrice,
                                      @RequestParam("ratingMin") Optional<Integer> ratingMin,
                                      @RequestParam("authors") Optional<List<Integer>> authors,
                                      @RequestParam("categories") Optional<List<Integer>> categories,
                                      @RequestParam("publishers") Optional<List<Integer>> publishers);



}
//    @RequestHeader(value="authors" , required = false) Optional<List<Integer>> authors,
//    @RequestHeader (value="categories" , required = false)Optional<List<Integer>> categories,
//    @RequestHeader (value="publishers" , required = false) Optional<List<Integer>> publishers);