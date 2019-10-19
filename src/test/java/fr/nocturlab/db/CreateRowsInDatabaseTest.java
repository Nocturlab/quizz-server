package fr.nocturlab.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Category;
import fr.nocturlab.entities.Question;
import fr.nocturlab.entities.Resource;
import fr.nocturlab.exception.NotFoundException;
import fr.nocturlab.repository.CategoryRepository;
import fr.nocturlab.repository.QuestionRepository;
import fr.nocturlab.repository.TypeResourceRepository;

public class CreateRowsInDatabaseTest {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	TypeResourceRepository typeResourceRepository;
	
	@Test
	public void createCategoriesTest() {
		List<Category> categs = new ArrayList<>();
		categs.add(new Category("Qui travaille avec qui ?"));
		categs.add(new Category("Qui a écrit l'article ?"));
		categs.add(new Category("Qui travaille sur ce projet?"));
		categs.add(new Category("Qui a fait quoi ?"));

		categoryRepository.saveAll(categs);
	}

	@Test
	@Ignore
	public void createQuestionsTest() throws NotFoundException {
		List<Question> questions = new ArrayList<>();
		Category whoWriteThisArticleCateg = categoryRepository.findByName("Qui a écrit l'article ?").orElseThrow(()->new NotFoundException("Category with name 'Qui a écrit l'article ?' doesn't exist."));
		questions.add(new Question("Qui a participé à l'écriture de l'article",
		new ArrayList<Answer>() {{
			add(new Answer("Remy Mullot"));
			add(new Answer("Alain Bouju"));
			add(new Answer("Patrick Franco"));
			add(new Answer("Armelle Prigent"));
			add(new Answer("Arnaud Revel"));
			add(new Answer("Karell Bertet"));
			add(new Answer("Damien Mondou"));
		}},
		0 /* 0 is the first answer in the list setted before */,
		whoWriteThisArticleCateg,
		new Resource("Analyse d’Images de Documents Anciens : une Approche Texture, revue Traitement du signal, Volume 24, N° 6, 2007", "Remy Mullot", typeResourceRepository.findByName("Article").orElseThrow(()->new NotFoundException("TypeResource with name 'Article' doesn't exist.")), "Ses activités de recherche se placent dans le cadre de la reconnaissance et l’indexation de documents, dès lors que ces processus sont associés à une analyse du contenu de l’image. Les cibles visées sont très larges puisqu’elles peuvent porter sur les documents anciens, mais plus largement sur tout type de documents fortement structurés (basés essentiellement sur des composantes textuelles) ou plus faiblement structurés (basé essentiellement sur des données graphiques). Ses thèmes de recherche sont très transversaux puisqu’ils portent aussi bien sur les extracteurs d’indices (signatures) que sur les systèmes de traitement, en passant par la catégorisation des contenus, des structures, des styles, ...")
		));

		questionRepository.saveAll(questions);
	}
}