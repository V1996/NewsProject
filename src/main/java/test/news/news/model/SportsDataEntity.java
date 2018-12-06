package test.news.news.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.boot.autoconfigure.web.ResourceProperties;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sports_data")
public class SportsDataEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "sports_id")
    @JsonBackReference
    private Sports sports;

    public Sports getSports() {
        return sports;
    }

    public void setSports(Sports sports) {
        this.sports = sports;
    }


    @Column(name="total_result")
    private int total_result;

    @Column(name="source_name")
    private String sourceName;

    @Column(name="auhtor")
    private String author;

    @Column(name="title")
    private String title;

    @Id
    @Column(name="description")
    private String description;

    @Column(name="url_to_image")
    private String urlToImage;


    @Column(name="published_at")
    private String publishedAt;

    @Column(name="content")
    private String content;

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
