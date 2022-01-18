package Commands.APIcommands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class getALL implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg.length == 4 && msg[0].equalsIgnoreCase("!s")) {
            if (msg[3].equalsIgnoreCase("getall") || msg[3].equalsIgnoreCase("sendall")) {


                String subreddit = msg[1];

                String query = msg[2];

                String[] URLarray = new String[6];

                HashSet<String> set = new HashSet<>();
                event.getChannel().type();


//        String fdfgd = "https://api.pushshift.io/reddit/search/submission/?q=jeanne&subreddit=animearmpits&sort=dec&size=500"; //just a string

                URLarray[0] = "https://api.pushshift.io/reddit/search/submission/?q=" + query + "&subreddit=" + subreddit + "&sort=desc&size=500";
                URLarray[1] = "https://api.pushshift.io/reddit/search/submission/?q=" + query + "&subreddit=" + subreddit + "&sort=asc&size=500";
                URLarray[2] = "https://api.pushshift.io/reddit/search/submission/?q==" + query + "&subreddit=" + subreddit + "&sort=desc&size=500&sort_type=score";
                URLarray[3] = "https://api.pushshift.io/reddit/search/submission/?q=" + query + "&subreddit=" + subreddit + "&sort=asc&size=500&sort_type=score";
                URLarray[4] = "https://api.pushshift.io/reddit/search/submission/?q=" + query + "&subreddit=" + subreddit + "&sort=desc&size=500&sort_type=num_comments";
                URLarray[5] = "https://api.pushshift.io/reddit/search/submission/?q=" + query + "&subreddit=" + subreddit + "&sort=asc&size=500&sort_type=num_comments";

                for (int i = 0; i < URLarray.length; i++) {

                    URL url = null;
                    try {
                        url = new URL(URLarray[i]);
                        URLConnection request = url.openConnection();
                        request.connect();

                        // Convert to a JSON object to print data
                        JSONParser jp = new JSONParser(); //from gson
                        JSONObject root = (JSONObject) jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                        JSONObject rootobj = new JSONObject(root);
                        JSONArray array = (JSONArray) rootobj.get("data");


                        ArrayList<String> link = new ArrayList<>();
                        ArrayList<String> link1 = new ArrayList<>();
                        ArrayList<String> link2 = new ArrayList<>();
                        ArrayList<String> link3 = new ArrayList<>();
                        ArrayList<String> link4 = new ArrayList<>();
                        ArrayList<String> link5 = new ArrayList<>();


                        for (int j = 0; j < array.size(); j++) {
                            JSONObject obj = (JSONObject) array.get(j);

                            if (i == 0) {
                                link.add(obj.get("url").toString() + "\n");
                            } else if (i == 1) {
                                link1.add(obj.get("url").toString() + "\n");
                            } else if (i == 2) {
                                link2.add(obj.get("url").toString() + "\n");
                            } else if (i == 3) {
                                link3.add(obj.get("url").toString() + "\n");
                            } else if (i == 4) {
                                link4.add(obj.get("url").toString() + "\n");
                            } else {
                                link5.add(obj.get("url").toString() + "\n");
                            }
                        }

                        set.addAll(link);
                        set.addAll(link1);
                        set.addAll(link2);
                        set.addAll(link3);
                        set.addAll(link4);
                        set.addAll(link5);

                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                }
                String[] union = {};
                union = set.toArray(union);


                StringBuffer result = new StringBuffer();
                for (int i = 0; i < union.length; i++) {
                    result.append(union[i]);
                    //result.append( optional separator );
                }
                String mynewstring = result.toString();

                if (msg[3].equalsIgnoreCase("getall")) {
                    event.getChannel().sendMessage("```" + mynewstring + "```");
                } else {
                    event.getChannel().sendMessage(mynewstring);
                }
                if (union.length>60) {
                    for (int i=0; i< union.length;i++) {
                        event.getChannel().sendMessage(union[i]);
                    }
                }
                event.getChannel().sendMessage(String.valueOf(union.length)+ " results found");
            }
        }
    }
}
