package fr.nocturlab.web;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.nocturlab.entities.Account;
import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Question;
import fr.nocturlab.entities.Resultat;
import fr.nocturlab.exception.NotFoundException;
import fr.nocturlab.manager.AccountManager;
import fr.nocturlab.repository.AccountRepository;
import fr.nocturlab.repository.AnswerRepository;
import fr.nocturlab.repository.QuestionRepository;
import fr.nocturlab.repository.ResultatRepository;
import fr.nocturlab.utils.MappingUtil;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = {"api/v2"})
public class ApiHandler {

	@Autowired
	public Environment env;
	
	@Autowired
	public MappingUtil mapping;

	@Autowired
	private AccountManager accountManager;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Value("${server.https}")
	public boolean https;

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseEntity<Account> login(@RequestHeader(name = "Auth", defaultValue = "") String auth) {
		String[] identifiants = accountManager.parseAuth(auth);
		if (identifiants.length != 2)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		// Pseudo Password
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok(a);
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	public ResponseEntity<Account> initAccounts(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		Account account = mapping.create(Account.class, params);
		account.setPass(accountManager.encryptPassword((String)params.get("pass")));
		Account a = accountRepository.save(account);

		return ResponseEntity.ok(a);
	}

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	ResultatRepository resultatRepository;
	@Autowired
	MappingUtil mappingUtil;

	@RequestMapping(value = { "/questions/{questionId}" }, method = RequestMethod.GET)
	public ResponseEntity<?> getQuestion(@RequestHeader(name = "Auth", required = false) String auth,
			HttpServletRequest request, @PathVariable(name = "questionId", required = true) int questionId)
			throws NotFoundException {
		String[] identifiants = accountManager.parseAuth(auth);
		if (identifiants.length == 0)
			return ResponseEntity.badRequest().build();
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		Question question = questionRepository.findById(questionId).orElseThrow(()->new NotFoundException("Question with id: "+questionId+ " doesn't exist."));

		return ResponseEntity.ok(question);
	}

	@RequestMapping(value = { "/question" }, method = RequestMethod.GET)
	public ResponseEntity<?> getNextQuestion(@RequestHeader(name = "Auth", required = false) String auth,
			HttpServletRequest request) throws NoSuchAlgorithmException {
		String[] identifiants = accountManager.parseAuth(auth);
		if(identifiants.length == 0)
			return ResponseEntity.badRequest().build();
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		List<Question> questions = questionRepository.findByNotAlreadyAnswer(a);
		Question question = questions.get(SecureRandom.getInstance("SHA1PRNG").nextInt(questions.size()));
		return ResponseEntity.ok(question);
	}

	@RequestMapping(value = { "/questions/{questionId}/answer" }, method = RequestMethod.POST)
	public ResponseEntity<?> postAnswer(@RequestHeader(name = "Auth", required = false) String auth,
			HttpServletRequest request, 
			@PathVariable(name = "questionId", required = true) int questionId,
			@RequestBody List<Integer> data
	) throws NotFoundException {
		String[] identifiants = accountManager.parseAuth(auth);
		if(identifiants.length == 0)
			return ResponseEntity.badRequest().build();
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		ArrayList<Answer> answers = new ArrayList<>();
		boolean isFirst = true;
		int duration = -1;
		for (Integer answer : data) {
			if(isFirst){ // first is duration in second
				isFirst = false;
				duration = answer;
			}else
				answers.add(answerRepository.findById(answer).orElseThrow(()->new NotFoundException("Answer with id: "+answer+ " doesn't exist.")));
		}
		Question question = questionRepository.findById(questionId).orElseThrow(()->new NotFoundException("Question with id: "+questionId+ " doesn't exist."));
		
		Resultat resultat = new Resultat(a, question, answers, duration);
		resultatRepository.save(resultat);

		boolean isCorrect = question.isCorrect(answers);
		if(isCorrect){
			a.incDifficulty(question.getDifficulty());
			question.incDifficulty(a.getDifficulty());
		}else{
			a.decDifficulty(question.getDifficulty());
			question.decDifficulty(a.getDifficulty());
		}

		return ResponseEntity.ok(isCorrect);
	}

	@RequestMapping(value = { "/questions/{questionId}/answer" }, method = RequestMethod.GET)
	public ResponseEntity<?> getResultat(@RequestHeader(name = "Auth", required = false) String auth,
			HttpServletRequest request, 
			@PathVariable(name = "questionId", required = true) int questionId
	) throws NotFoundException {
		String[] identifiants = accountManager.parseAuth(auth);
		if(identifiants.length == 0)
			return ResponseEntity.badRequest().build();
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		Question question = questionRepository.findById(questionId).orElseThrow(()->new NotFoundException("Question with id: "+questionId+ " doesn't exist."));
		
		Resultat resultat = resultatRepository.findByAccountAndQuestion(a, question).orElseThrow(()->new NotFoundException("Resultat with account: "+a.getId()+ "and account: "+question.getId()+ " doesn't exist."));

		return ResponseEntity.ok(resultat);
	}

	@RequestMapping(value = { "/answers" }, method = RequestMethod.GET)
	public ResponseEntity<?> getResultats(@RequestHeader(name = "Auth", required = false) String auth,
			HttpServletRequest request,
			@PathVariable(name = "questionId", required = true) int questionId
	) throws NotFoundException {
		String[] identifiants = accountManager.parseAuth(auth);
		if(identifiants.length == 0)
			return ResponseEntity.badRequest().build();
		Account a = accountManager.login(identifiants[0], identifiants[1]);
		if (a == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		Question question = questionRepository.findById(questionId).orElseThrow(()->new NotFoundException("Question with id: "+questionId+ " doesn't exist."));
		
		Resultat resultat = resultatRepository.findByAccountAndQuestion(a, question).orElseThrow(()->new NotFoundException("Resultat with account: "+a.getId()+ "and account: "+question.getId()+ " doesn't exist."));

		return ResponseEntity.ok(resultat);
	}
}