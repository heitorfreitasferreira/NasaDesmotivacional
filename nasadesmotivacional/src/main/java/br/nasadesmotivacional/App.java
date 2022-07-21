package br.nasadesmotivacional;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import br.nasadesmotivacional.models.Tweet;
import br.nasadesmotivacional.services.imageprocessing.Frases;
import br.nasadesmotivacional.services.imageprocessing.Imagem;
import br.nasadesmotivacional.services.nasa.FetchNasa;
import br.nasadesmotivacional.services.twitter.ResponseNasa;
import br.nasadesmotivacional.services.twitter.TwitterService;
import lombok.Getter;

public final class App {

    @Getter
    private static TwitterService twitterService;

    public static void oldMain(String[] args) {
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

    public static void main(String[] args) throws Exception {
        twitterService = new TwitterService();

        FetchNasa fetchNasa = FetchNasa.getInstance();
        ResponseNasa responseNasa = fetchNasa.requestJSON();

        InputStream inputStream = new URL(responseNasa.getUrl()).openStream();
        Imagem gerador = new Imagem();
        File image = gerador.cria(inputStream, "tweetImage", Frases.getInstance().get().toUpperCase());

        Tweet tweet = new Tweet("Texto do tweet", image);
        getTwitterService().tweet(tweet);
    }

}
