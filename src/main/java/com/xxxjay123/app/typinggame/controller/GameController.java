package com.xxxjay123.app.typinggame.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xxxjay123.app.typinggame.entity.Algorithm;
import com.xxxjay123.app.typinggame.entity.model.Score;
import com.xxxjay123.app.typinggame.model.AlgorithmDTO;
import com.xxxjay123.app.typinggame.service.AlgorithmService;
import com.xxxjay123.app.typinggame.service.ScoreService;
import com.xxxjay123.app.typinggame.util.Language;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/game")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GameController {
  @Autowired
  private final AlgorithmService algorithmService;
  @Autowired
  private final ScoreService scoreService;



  @GetMapping("/algorithm")
  public ResponseEntity<List<Algorithm>> getWordsByLanguage(
     @RequestParam String language) {
    try {
      Language enumlanguage = Language.valueOf(language.toUpperCase());
      List<Algorithm> algorithm =
          algorithmService.getWordsByLanguage(enumlanguage);
      return ResponseEntity.ok(algorithm);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }
  }

  @GetMapping("/random-algorithm")
  public ResponseEntity<List<Algorithm>> getRandom() {
    List<Algorithm> algorithm = algorithmService.getRandom();
    return ResponseEntity.ok(algorithm);
  }

  @PostMapping("/scores")
  public ResponseEntity<Void> saveScore(@RequestBody Score score) {
    scoreService.addScore(score);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/scores")
  public ResponseEntity<List<Score>> getScores() {
    List<Score> scores = scoreService.getScores();
    return ResponseEntity.ok(scores);
  }

  @CrossOrigin(origins = "*")
  @PostMapping("/upload-algorithm")
  public ResponseEntity<Algorithm> createAlgorithm(
      @RequestBody AlgorithmDTO algorithmDTO) {
    Algorithm algorithm = new Algorithm();
    algorithm.setLanguage(Language.valueOf(algorithmDTO.getLanguage())); // Assuming getLanguage() returns a string representing Language enum
    algorithm.setAlgorithm(algorithmDTO.getAlgorithm());

    Algorithm createdAlgorithm = algorithmService.createAlgorithm(algorithm);
    return ResponseEntity.ok(createdAlgorithm);
  }

}
