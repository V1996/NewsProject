package test.news.news.controller;

import test.news.news.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.news.news.model.Sports;
import test.news.news.serviceImpl.NewsServiceImpl;

import java.util.List;


@RestController

public class HomeController {
    @Autowired
    NewsServiceImpl newsService;

    @RequestMapping(value ="/news",method = RequestMethod.GET)
    public Response getNews(){
        return newsService.getAll();
    }

    @RequestMapping(value="/news/{category}")
    public Response getSportsNews(@PathVariable String category){
        return newsService.getSportsNews(category);
    }

    @RequestMapping(value="/data",method = RequestMethod.GET)
    public List<Sports> getData(){
      return newsService.getDataDB();
    }

    @RequestMapping(value="/delete",method = RequestMethod.GET)
    public void getDel(){
         newsService.getDeleteNews();
    }
}


