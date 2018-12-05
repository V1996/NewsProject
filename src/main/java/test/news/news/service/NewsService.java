package test.news.news.service;

import test.news.news.model.Response;

public interface NewsService {

    public Response getAll();

    public Response getSpecificNews(String category);
}
