package br.nasadesmotivacional.services.twitter;

import br.nasadesmotivacional.models.Tweet;
import lombok.Getter;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.UploadedMedia;

public class TwitterService {

  @Getter
  private Twitter twitter;

  public TwitterService() {
    this.twitter = new TwitterFactory().getInstance();
  }

  public void tweet(Tweet tweet) {

    try {
      UploadedMedia media = getTwitter().uploadMedia(tweet.getImageFile());
      long[] mediaIds = new long[1];
      mediaIds[0] = media.getMediaId();

      StatusUpdate status = new StatusUpdate(tweet.getMessage());
      status.setMediaIds(mediaIds);

      getTwitter().updateStatus(status);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
