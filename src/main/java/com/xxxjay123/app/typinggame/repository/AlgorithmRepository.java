package com.xxxjay123.app.typinggame.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.xxxjay123.app.typinggame.entity.Algorithm;
import com.xxxjay123.app.typinggame.util.Language;


public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {
  @Query("SELECT a FROM Algorithm a WHERE a.language = :language")
  List<Algorithm> findByLanguage(@Param("language") Language language);

  @Query(value = "SELECT * FROM Algorithm ORDER BY RAND() LIMIT 1",
      nativeQuery = true)
  List<Algorithm> findRandomAlgorithm();
}
