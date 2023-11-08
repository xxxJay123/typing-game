package com.xxxjay123.app.typinggame.entity;

import com.xxxjay123.app.typinggame.util.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "algorithm")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Algorithm {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Language language;

 @Column(columnDefinition = "LONGTEXT")
  private String algorithm;

}
