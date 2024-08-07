package bg.softuni.healthheaven.scheduler;


import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.model.entities.BaseEntity;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CronScheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);
    private final ArticleRepository articleRepository;


    @Scheduled(cron = "0 0 1 * *")//1am
    public void deleteArticlesMoreThanMonth() {

        List<Article> articles = this.articleRepository.findAll();
        LocalDateTime scheduledDate = LocalDateTime.now().minusMonths(1L);

        List<Long> ids = articles.stream()
            .filter(article -> {

                LocalDateTime created = LocalDateTime.from(article.getTimeOnPost());



                return created.isBefore(scheduledDate);

            })
            .map(BaseEntity::getId).toList();

        this.articleRepository.deleteAllById(ids);

        LOGGER.info("Deleted articles which are posted before more than 1 month!");
    }


}
