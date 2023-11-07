package com.xxxjay123.app.typinggame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xxxjay123.app.typinggame.entity.Algorithm;
import com.xxxjay123.app.typinggame.repository.AlgorithmRepository;
import com.xxxjay123.app.typinggame.util.Language;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
  @Autowired
  private final AlgorithmRepository algorithmRepository;

  public List<Algorithm> getWordsByLanguage(Language topic) {
    return algorithmRepository.findByLanguage(topic);
  }

  public List<Algorithm> getRandom() {
    return algorithmRepository.findRandomAlgorithm();
  }

  public Algorithm createAlgorithm(Algorithm algorithm) {
    return algorithmRepository.save(algorithm);
  }
}
