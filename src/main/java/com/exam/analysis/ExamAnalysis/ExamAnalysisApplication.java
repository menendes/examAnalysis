package com.exam.analysis.ExamAnalysis;

import com.exam.analysis.ExamAnalysis.view.HomePage;
import lombok.var;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class ExamAnalysisApplication extends JFrame {

	ExamAnalysisApplication(){
		initUI();
	}

	private void initUI() {
		HomePage homePage = new HomePage();
	}

	public static void main(String[] args) {

		var ctx = new SpringApplicationBuilder(ExamAnalysisApplication.class)
				.headless(false).run(args);

	}

}
