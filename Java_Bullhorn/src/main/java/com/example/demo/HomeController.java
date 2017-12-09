package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public class HomeController {

    @Autowired
    TweetRepository tweetRepository;

    @RequestMapping
    public String listTweets(Model model){
        model.addAttribute("tweets", tweetRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String tweetForm(Model model){
        model.addAttribute("tweet", new Tweet());
        return "tweetform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Tweet tweet, BindingResult result){
        if (result.hasErrors()){
            return "tweetform";
        }
        tweetRepository.save(tweet);
        return "redirect:/";
    }
}
