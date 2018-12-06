package test.news.news.service;

import test.news.news.model.Response;
import test.news.news.model.Sports;

import java.util.List;

public interface NewsService {

    public Response getAll();

    public Response getSportsNews(String category);

    public List<Sports> getDataDB();

    public void getDeleteNews();
}
