package com.test;

import facebook4j.Account;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class PostsFromPageExtractor {

/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 */
public static void main(String[] args) throws FacebookException {

    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId("146949149375799", "443aa4f14bace9fc581e164e9507b515");
    // Get an access token from: 
    // https://developers.facebook.com/tools/explorer
    // Copy and paste it below.
    String accessTokenString = "EAACFpkPZAPTcBAKSwNZCz4fyp0ZCE1UXImRpPyDc0Kn7JxNHe61cAzGErWmqvhfRCLDYm087ZBdgZBfZCVCZCGvjiv38eQ6Mbtd0lDT9QDMpZBsJQQDbjPIvtLfqehyeKu3cGaMuzvUDbxt6YNJTdOS2ZBZBJILZCoCDLhwBXBaQAcefHbdMcg1B9xFrNQbmAWlnEEZD";
    AccessToken at = new AccessToken(accessTokenString);
    // Set access token.
    facebook.setOAuthAccessToken(at);

    // We're done.
    // Access group feeds.
    // You can get the group ID from:
    // https://developers.facebook.com/tools/explorer

    // Set limit to 25 feeds.
//    ResponseList<Post> list= facebook.getStatuses();
//    System.out.println(list.size());
//    for (int i = 0; i < list.size(); i++) {
//    	Post ac=(Post) list.get(i);
//    	System.out.println(ac.getStory());
//	}
//    System.out.println("aaa");
//    ResponseList<Post> feeds=facebook.getFeed();
    System.out.println(facebook.getFeed());
    ResponseList<Post> feeds = facebook.getFeed("187446750783",
            new Reading().limit(25));
System.out.println(feeds.size());
        // For all 25 feeds...
        for (int i = 0; i < feeds.size(); i++) {
            // Get post.
            Post post = feeds.get(i);
            // Get (string) message.
            String message = post.getMessage();
                            // Print out the message.
            System.out.println(message);

            // Get more stuff...
            PagableList<Comment> comments = post.getComments();
//            Comment c=(Comment)comments.get(0);
//            System.out.println(post.getLikes());
//            System.out.println(comments);
            String date = post.getCreatedTime().toString();
//            String name = post.getFrom().getName();
            String id = post.getId();
            System.out.println();
            
//            System.out.println("***"+id);
        }           
    }
}