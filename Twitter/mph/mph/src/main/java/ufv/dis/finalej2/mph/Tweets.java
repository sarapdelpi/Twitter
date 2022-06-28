package ufv.dis.finalej2.mph;

import java.util.ArrayList;
import java.util.Collection;

public class Tweets {

 		private Collection<Tweet> tweets;

 		public Tweets() {
 			this.tweets = new ArrayList<>();
 		}
 		
 		
		public Collection<Tweet> getTweets() {
			return tweets;
		}
		
		public boolean addTweets(Tweet nuevo) {
			tweets.add(nuevo);
			return true;
		}

		
 		
}
