package br.nasadesmotivacional.services.imageprocessing;

import java.util.ArrayList;

public class Frases {
  private static Frases instance;
  private static ArrayList<String> livres = new ArrayList<String>();
  private static ArrayList<String> usadas = new ArrayList<String>();

  private Frases() {
    insert("Veja como tudo acabou ontem e se decepcione mais um dia!");
    insert("Continue tentando se iludir com frases motivacionais enquanto a realidade acontece lá fora");
    insert("Gratidão por todos os conselhos motivacionais que me fizeram desmotivar");
    insert("Agora e daqui pra frente, é só ladeira abaixo");
    insert("Quando a vida parecer difícil lembre-se que tudo pode piorar");
    insert("Não desanime, as piores coisas da vida ainda estão chegando por aí!");
    insert("Vencedores nunca param de se vangloriar por suas vitórias. Como sei? Eu vejo, nunca ganhei");
    insert("Aprendi da maneira mais difícil que nasci pra tentar, tentar, tentar e nunca conseguir");
    insert("Nunca tente piorar o que já está ruim. Deixe que a vida se encarrega de fazer isso pra você");
    insert("Nunca é tarde demais para errar mais uma vez");
    insert("Não se preocupe se você perder, nós não esperávamos mais de você!");
    insert("Pare de reclamar de tudo e comece a aceitar que tudo deu errado pra você!");
    insert("Procrastinar é sempre a melhor escolha para você se sentir um completo imbecil");
    insert("O caminho para o sucesso não está no seu mapa da vida. Aceite!");
    insert("A vida é realmente muito mais difícil para quem é um completo idiota.");
    insert("Pare de tratar como lição mais uma das suas falhas");
    insert("Mais um dia que se inicia e novas oportunidades para errar surgem com esse novo amanhecer!");
    insert("Há sempre um espaço inútil para não ser aproveitado na vida de quem não tem o que fazer");
    insert("Pare e pense: todas as suas falhas tem o mesmo causador em comum: Você!");
    insert("Todo mundo tem um propósito na vida mas nem todo mundo vai encontrá-lo. Faz parte");
  }

  public static Frases getInstance() {
    if (instance == null) {
      instance = new Frases();
    }
    return instance;
  }

  public static void insert(String frase) {
    int count = 0;
    String retorno = "";

    for (String word : frase.split(" ")) {
      retorno += " " + word;

      if (count > 4) {
        retorno += "\n";
        count = 0;
      }

      count++;
    }

    livres.add(retorno);
  }

  public String get() {
    if (livres.isEmpty() && usadas.isEmpty())
      return "";
    if (livres.isEmpty()) {
      livres.addAll(usadas);
      usadas.clear();
    }
    int randomIndex = (int) (Math.random() * livres.size());
    String frase = livres.get(randomIndex);
    usadas.add(frase);
    livres.remove(randomIndex);
    return frase;
  }
}
