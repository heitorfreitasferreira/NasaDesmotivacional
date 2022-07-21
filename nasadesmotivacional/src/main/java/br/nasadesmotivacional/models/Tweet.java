package br.nasadesmotivacional.models;

import java.io.File;

import lombok.*;

@Data
@AllArgsConstructor
public class Tweet {
  private String message;
  private File imageFile;
}