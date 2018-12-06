package test.news.news.serviceImpl;

import test.news.news.common.ApiConstants;
import test.news.news.dao.SportsDao;
import test.news.news.daoImpl.SportsDaoImpl;
import test.news.news.model.Articles;
import test.news.news.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import test.news.news.model.Sports;
import test.news.news.model.SportsDataEntity;
import test.news.news.service.NewsService;

import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private Environment environment;

    @Autowired
    private SportsDao sportsDao;

    @Override
    public Response getAll() {
        String url = environment.getProperty(ApiConstants.NEWS_URL);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uri, Response.class);
               Response newsResponse = responseEntity.getBody();
            return  newsResponse;
        } catch (Exception e) {

            return null;
        }

    }

    @Override
    public Response getSportsNews(String category) {
        String url = environment.getProperty(ApiConstants.NEWS_URL_SPECIFIC);
        try {
            url = url + URLEncoder.encode(category, "UTF-8");
            url=url+"&apiKey=c9f34fdef1a2457b9a74c479d2bf272a";
        }
        catch (Exception e){
            System.out.println(e);
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Response> responseEntity = restTemplate.getForEntity(uri, Response.class);
            Response newsResponse = responseEntity.getBody();
            Sports sports=new Sports();
            sports.setCategory(category);
            List<SportsDataEntity> sportsDataEntities=getList(newsResponse,sports);
            sports.setSportsDataEntities(sportsDataEntities);
            sportsDao.save(sports);
            return  newsResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Sports> getDataDB() {
        return (List)sportsDao.findAll();
    }

    @Override
    public void getDeleteNews() {
        List<Sports> list = (List) sportsDao.findAll();
        LocalDate date = LocalDate.now();
        date = date.plusDays(-10);
        Date date1 = java.sql.Date.valueOf(date);
        for (Sports lists:list){
            List<SportsDataEntity> result = lists.
                    getSportsDataEntities().stream()
                    .filter(data -> date1.before(getDate(data)))
                    .collect(Collectors.toList());
            sportsDao.delete(lists);
            lists.setSportsDataEntities(result);
            sportsDao.save(lists);
        }


    }

        public Date getDate(SportsDataEntity s) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s.getPublishedAt());
                return date;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }





       /* for(int i=0;i<list.get(0).getSportsDataEntities().size();i++){
            String resDate=list.get(0).getSportsDataEntities().get(i).getPublishedAt().substring(0,10);
            try {
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(resDate);
                    if(date2.compareTo(date1)>0){
                        list.get(0).getSportsDataEntities().remove(list.get(0).getSportsDataEntities().get(i));
                    }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }*/
       // System.out.println(list.toString());

   // }

    public List<SportsDataEntity> getList(Response response,Sports sports){
        List<SportsDataEntity> sportsDataEntity=new ArrayList<SportsDataEntity>();
        for(int i=0;i<20;i++){
            SportsDataEntity sportsDataEntity1=new SportsDataEntity();
            sportsDataEntity1.setSports(sports);
            sportsDataEntity1.setSourceName(response.getArticles().get(i).getSource().getName());
            sportsDataEntity1.setTotal_result(response.getTotalResults());
            sportsDataEntity1.setAuthor("golu");
            sportsDataEntity1.setDescription(response.getArticles().get(i).getDescription().substring(0,5));
            sportsDataEntity1.setPublishedAt(response.getArticles().get(i).getPublishedAt().substring(0,15));
            sportsDataEntity1.setTitle(response.getArticles().get(i).getTitle().substring(0,15));
            sportsDataEntity1.setUrlToImage(response.getArticles().get(i).getUrlToImage().substring(0,15));
            sportsDataEntity1.setContent("chal gya");
            sportsDataEntity.add(sportsDataEntity1);
        }
        return sportsDataEntity;
    }

}
