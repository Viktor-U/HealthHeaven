package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleExportDTO>> getAllArticles() {

        return ResponseEntity.ok(articleService.getAllArticles());
    }

}
