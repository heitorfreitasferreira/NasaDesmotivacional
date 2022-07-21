package br.nasadesmotivacional.services.imageprocessing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import br.nasadesmotivacional.services.nasa.FetchNasa;

public class Imagem {
  private static int FONT_SIZE = 12;

  public File cria(InputStream fileObject, String nomeArquivo, String frase) throws Exception {
    return cria(fileObject, nomeArquivo, frase, false);
  }

  public File cria(InputStream fileObject, String nomeArquivo, String frase, boolean save) throws Exception {

    // leitura da imagem

    // BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme.jpg"));
    // InputStream fileObject = new FileInputStream(new File("entrada/filme.jpg"));
    BufferedImage imagemOriginal = ImageIO.read(fileObject);

    // criar nova imagem com transparencia e tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    if (largura > 700) {
      FONT_SIZE = 36;
    } else {
      FONT_SIZE = 12;

    }
    BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TRANSLUCENT);// Imagem de fundo
                                                                                             // vazio
    // copiar a imagem original para a nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.createGraphics();

    graphics.drawImage(imagemOriginal, 0, 0, null);
    // configurar a fonte

    Font fonte = new Font(Font.SERIF, Font.BOLD, FONT_SIZE);
    graphics.setFont(fonte);
    if (Math.random() > 0.5) {
      graphics.setColor(Color.WHITE);
    } else {
      graphics.setColor(Color.YELLOW);
    }
    // escrever uma frase na nova imagem
    int Ilength = (int) graphics.getFontMetrics(fonte).getStringBounds(frase, graphics).getWidth();
    int IHeight = (int) graphics.getFontMetrics(fonte).getStringBounds(frase, graphics).getHeight();

    // graphics.drawString(frase, largura / 2 - Ilength / 2, altura / 2 + IHeight /
    // 2);
    drawString(graphics, frase, largura / 2 - getXPos(frase, graphics), altura / 2);
    if (save)
      ImageIO.write(novaImagem, "png", new File(nomeArquivo + ".png"));

    return new File(nomeArquivo + ".png");
  }

  private void drawString(Graphics2D g, String text, int x, int y) {
    for (String line : text.split("\n"))
      g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }

  int getXPos(String text, Graphics2D g) {
    int x = 0;
    for (String line : text.split("\n")) {
      x = Math.max(x, g.getFontMetrics().stringWidth(line));
    }
    return x / 2;
  }

  public static void main(String[] args) {
    try {
      InputStream inputStream = new URL(FetchNasa.getInstance().requestJSON().getUrl())
          .openStream();
      Imagem gerador = new Imagem();

      Frases frases = Frases.getInstance();
      File file = gerador.cria(inputStream, "filme", frases.get());
      System.out.println(file.getPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
