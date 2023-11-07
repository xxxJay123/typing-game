package com.xxxjay123.app.typinggame.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.xxxjay123.app.typinggame.entity.model.Score;

@Service
public class ScoreService {
  private List<Score> scores = new ArrayList<>();

  public void addScore(Score score) {
    scores.add(score);
  }

  public List<Score> getScores() {
    return scores;
  }
}
