package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public List<ArticleExportDTO> getAllArticles() {

        List<Article> all = articleRepository.findAll();

        List<ArticleExportDTO> result = new ArrayList<>();

        for (Article e : all) {
           result.add(modelMapper.map(e, ArticleExportDTO.class));
        }

        return result;
    }
}

