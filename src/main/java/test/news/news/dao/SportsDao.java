package test.news.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test.news.news.model.Response;
import test.news.news.model.Sports;

public interface SportsDao extends JpaRepository<Sports,Integer> {


}
