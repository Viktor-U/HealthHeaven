package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.articles.ArticleDTO;
import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.services.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleExportDTO>> getAllArticles() {

        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/articles/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ArticleExportDTO> getOneArticle(@PathVariable Long id) {

        return ResponseEntity.ok(articleService.getOneArticle(id));
    }

    @PostMapping("/articles")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ArticleExportDTO> createArticle(@RequestBody ArticleDTO articleDTO) {

        ArticleExportDTO result = articleService.createArticle(articleDTO);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/articles/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }


}
