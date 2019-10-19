package fr.nocturlab.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.nocturlab.entities.Category;
import fr.nocturlab.entities.Question;
import fr.nocturlab.repository.CategoryRepository;
import fr.nocturlab.repository.QuestionRepository;

public class CreateRowsInDatabaseTest {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	QuestionRepository questionRepository;
	
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
	public void createQuestionsTest() {
		List<Question> questions = new ArrayList<>();
		Category whoWriteThisArticleCateg = categoryRepository.findByName("Qui a écrit l'article ?")
			.orElseThrow(()->NotFoundException("Category with name 'Qui a écrit l'article ?' doesn't exist."));

		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer());
		answers.add(new Answer());
		answers.add(new Answer());
		answers.add(new Answer());
		questions.add(new Question("Qui a écrit cet article ?", ));

		questionRepository.saveAll(questions);
	}
}