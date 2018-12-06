package test.news.news.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.news.news.serviceImpl.NewsServiceImpl;

@Component
public class DeleteSchedular {
    @Autowired
    NewsServiceImpl newsService;
}
  /*  @Scheduled(cron = "${sports.data.cron.time}")
    private void process() {
        new Thread(() -> {
            newsService.getDeleteNews("ancv");
        }).start();
    }
}*/
