package com.moh.quizservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moh.quizservice.dao.QuizDao;
import com.moh.quizservice.feign.QuizInterface;
import com.moh.quizservice.model.QuestionWrapper;
import com.moh.quizservice.model.Quiz;
import com.moh.quizservice.model.Responsee;

@Service
public class QuizService {

	@Autowired
	QuizDao quizdao;

	@Autowired
	QuizInterface quizinterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> questions = quizinterface.getQuestionForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizdao.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);

	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz = quizdao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizinterface.getQuestionFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Responsee> responses) {
		ResponseEntity<Integer> score = quizinterface.getScore(responses);
		return score;
	}

}
