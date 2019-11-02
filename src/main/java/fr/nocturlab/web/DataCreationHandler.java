package fr.nocturlab.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Category;
import fr.nocturlab.entities.Question;
import fr.nocturlab.entities.Resource;
import fr.nocturlab.entities.TypeResource;
import fr.nocturlab.exception.NotFoundException;
import fr.nocturlab.repository.AnswerRepository;
import fr.nocturlab.repository.CategoryRepository;
import fr.nocturlab.repository.QuestionRepository;
import fr.nocturlab.repository.ResourceRepository;
import fr.nocturlab.repository.TypeResourceRepository;

@RestController
public class DataCreationHandler {
	@Value("${server.https}")
	public boolean https;

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	TypeResourceRepository typeResourceRepository;
	@Autowired
	ResourceRepository resourceRepository;
	@Autowired
	AnswerRepository answerRepository;

	@RequestMapping(value = { "/create-data" }, method = RequestMethod.GET)
	public ResponseEntity<String> createData(
		@RequestHeader(name = "Auth", defaultValue = "") String auth
	)throws NotFoundException {

		List<Category> categs = new ArrayList<Category>() {{
			add(new Category("Qui travaille avec qui ?"));
			add(new Category("Qui a écrit l'article ?"));
			add(new Category("Qui travaille sur ce projet ?"));
			add(new Category("Qui a fait quoi ?"));
		}};
		categoryRepository.saveAll(categs);

		List<TypeResource> types = new ArrayList<TypeResource>() {{
			add(new TypeResource("Article"));
			add(new TypeResource("Librairie"));
			add(new TypeResource("Personne"));
			add(new TypeResource("Image"));
		}};
		typeResourceRepository.saveAll(types);

		List<Question> questions = new ArrayList<Question>() {{
			add(new Question("Qui a participé à l'écriture de l'article ?",
				answerRepository.saveAll(new ArrayList<Answer>() {{
					add(new Answer("Remy Mullot"));
					add(new Answer("Alain Bouju"));
					add(new Answer("Patrick Franco"));
					add(new Answer("Armelle Prigent"));
					add(new Answer("Arnaud Revel"));
					add(new Answer("Karell Bertet"));
					add(new Answer("Damien Mondou"));
				}}),
				0 /* 0 is the first answer in the list setted before */,
				categoryRepository.findByName("Qui a écrit l'article ?").orElseThrow(()->new NotFoundException("Category with name 'Qui a écrit l'article ?' doesn't exist.")),
				resourceRepository.save(new Resource("Analyse d’Images de Documents Anciens : une Approche Texture, revue Traitement du signal, Volume 24, N° 6, 2007", 
					"Remy Mullot",
					typeResourceRepository.findByName("Article").orElseThrow(()->new NotFoundException("TypeResource with name 'Article' doesn't exist.")),
					"Ses activités de recherche se placent dans le cadre de la reconnaissance et l’indexation de documents, dès lors que ces processus sont associés à une analyse du contenu de l’image. Les cibles visées sont très larges puisqu’elles peuvent porter sur les documents anciens, mais plus largement sur tout type de documents fortement structurés (basés essentiellement sur des composantes textuelles) ou plus faiblement structurés (basé essentiellement sur des données graphiques). Ses thèmes de recherche sont très transversaux puisqu’ils portent aussi bien sur les extracteurs d’indices (signatures) que sur les systèmes de traitement, en passant par la catégorisation des contenus, des structures, des styles, ..."
				))
			));
			add(new Question("Qui développe la librairie de manipulation des treillis ?",
				answerRepository.saveAll(new ArrayList<Answer>() {{
					add(new Answer("Remy Mullot"));
					add(new Answer("Alain Bouju"));
					add(new Answer("Patrick Franco"));
					add(new Answer("Armelle Prigent"));
					add(new Answer("Arnaud Revel"));
					add(new Answer("Karell Bertet"));
					add(new Answer("Damien Mondou"));
				}}),
				5,
				categoryRepository.findByName("Qui travaille sur ce projet ?").orElseThrow(()->new NotFoundException("Category with name 'Qui travaille sur ce projet ?' doesn't exist.")),
				resourceRepository.save(new Resource("Librairie de manipulation des treillis", 
					"Karell Bertet",
					typeResourceRepository.findByName("Librairie").orElseThrow(()->new NotFoundException("TypeResource with name 'Librairie' doesn't exist.")),
					"Usages des treillis pour des données image :\n"+
					"Fouille de données. La méthode Navigala, méthode de classification supervisée par navigation dans un treillis, et intégrée dans un logiciel du même nom, a été développée pour de la reconnaissance d’images de symboles. Cependant, cette méthode peut s’utiliser pour tout type de données. Les treillis générés par cette méthode forment une classe de treillis aux propriétés particulières, appelés treillis dichotomiques. Nous proposons également : un mécanisme de génération à la demande de la structure de treillis ; une structure hybride entre treillis et arbre de classification pour réduire la taille de la structure tout en maintenant les taux de classification ; une signature structurelle robuste pour des images de symboles.\n"+
					"Recherche d’information et représentation des connaissances. Nous proposons un mécanismes de recherche d’information interactive et par facettes, plus précisément par navigation dans un structure de treillis. Cette méthode a été étudiée pour des données hétérogènes issues de bandes dessinées, et structurées sous forme d’une ontologie. Ces travaux, qui font suite à la mise en place d’une ontologie pour des images de lettrines, permettent d’intégrer à la fois des données bas-niveau extraites des images, une information spatiale, ainsi que la sémantique du domaine. Des possibilités d’annotation automatique, et d’enrichissement du modèle, rendus possibles via le mécanisme interactif de recherche d’information, sont également étudiés.\n"+
					"Indexation. Nous proposons des méthodes de réduction de l’espace de caractéristiques, appliquées à une représentation des images par sacs de mots visuels. Ces méthodes permettent de réduire les mots visuels tout en maintenant l’isomorphisme de l’espace de recherche sous-jacent, qui possède la structure de treillis. En particulier, nous étudions la réduction logique, ainsi que l’utilisation de générateurs minimaux.\n"+
					"Aspects structurels et algorithmiques des treillis :\n"+
					"Etude de la base canonique directe : Après avoir formellement défini cette base issue de l’identité entre 5 bases existantes, nous développons une algorithmique dédiée utilisant les propriétés spécifiques de cette base : calcul de fermeture améliorée, génération incrémentale efficace, ...\n"+
					"Développement d’une bibliothèque : Nous avons développé une bibliothèque Java, appelé lattice, qui propose un jeu algorithmique issu de la théorie des treillis pour manipuler efficacement les objets que sont le treillis, la table binaire ou encore un système de règles. Cette bibliothèque intègre également de nouveaux algorithmes permettant de manipuler la base canonique directe. Elle est diffusée en open source sur la plateforme github\n"
				))
			));
			add(new Question("Qui sont les directeurs de thèse de Damien Mondou ?",
				 answerRepository.saveAll(new ArrayList<Answer>() {{
					add(new Answer("Remy Mullot"));
					add(new Answer("Alain Bouju"));
					add(new Answer("Patrick Franco"));
					add(new Answer("Armelle Prigent"));
					add(new Answer("Arnaud Revel"));
					add(new Answer("Karell Bertet"));
					add(new Answer("Damien Mondou"));
				}}),
				new ArrayList<Integer>() {{
					add(3);
					add(4);
				}},
				categoryRepository.findByName("Qui travaille avec qui ?").orElseThrow(()->new NotFoundException("Category with name 'Qui travaille avec qui ?' doesn't exist.")),
				resourceRepository.save(new Resource("Damien Mondou", null, typeResourceRepository.findByName("Personne").orElseThrow(()->new NotFoundException("TypeResource with name 'Personne' doesn't exist."))))
			));
			add(new Question("Qui est impliqué dans les projets Art et sciences ?",
				answerRepository.saveAll(new ArrayList<Answer>() {{
					add(new Answer("Remy Mullot"));
					add(new Answer("Alain Bouju"));
					add(new Answer("Patrick Franco"));
					add(new Answer("Armelle Prigent"));
					add(new Answer("Arnaud Revel"));
					add(new Answer("Karell Bertet"));
					add(new Answer("Damien Mondou"));
				}}),
				4,
				categoryRepository.findByName("Qui a fait quoi ?").orElseThrow(()->new NotFoundException("Category with name 'Qui a fait quoi ?' doesn't exist."))
			));
			add(new Question("Qui est impliqué dans les projets Art et sciences ?",
				answerRepository.saveAll(new ArrayList<Answer>() {{
					add(new Answer("Remy Mullot"));
					add(new Answer("Alain Bouju"));
					add(new Answer("Patrick Franco"));
					add(new Answer("Armelle Prigent"));
					add(new Answer("Arnaud Revel"));
					add(new Answer("Karell Bertet"));
					add(new Answer("Damien Mondou"));
				}}),
				4,
				categoryRepository.findByName("Qui a écrit l'article ?").orElseThrow(()->new NotFoundException("Category with name 'Qui a écrit l'article ?' doesn't exist.")),
				resourceRepository.save(new Resource("« Une approche ontologique pour la structuration de données spatio-temporelles de trajectoires : Application à l’étude des déplacements de mammifères marins », Revue Internationale de Géomatique - International Journal of Geomatics and Spatial Analysis , Hermes-Lavoisier, vol 22/1-2012, pp 55-75 ( francophone ) (selected paper SAGEO)",
					null,
					typeResourceRepository.findByName("Article").orElseThrow(()->new NotFoundException("TypeResource with name 'Article' doesn't exist."))
				))
			));
		}};

		questionRepository.saveAll(questions);

		return ResponseEntity.ok("Datas was now created.");
	}
}