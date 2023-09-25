package com.moh.quizservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {

	String categoryName;
	Integer numQuestions;
	String title;

}
