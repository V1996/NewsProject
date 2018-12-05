package test.news.news.model;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Entity;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class Response {

    private String status;

    private int totalResults;

    private List<Articles> articles;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
