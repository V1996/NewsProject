package test.news.news.controller;

import test.news.news.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.news.news.serviceImpl.NewsServiceImpl;


@RestController

public class HomeController {
    @Autowired
    NewsServiceImpl newsService;

    @RequestMapping(value ="/news",method = RequestMethod.GET)
    public Response getNews(){
        return newsService.getAll();
    }

    @RequestMapping(value="/news/{category}")
    public Response getSpecificNews(@PathVariable String category){
        return newsService.getSpecificNews(category);
    }
}
