package Commands.APIcommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
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
import java.util.Random;

public class searchimage implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg.length==3 && msg[0].equalsIgnoreCase("!s"))
        {
            boolean isnsfw = event.getChannel().asServerTextChannel().get().isNsfw();

            String subreddit = msg[1];
            String query = msg[2];

            long start = System.currentTimeMillis();

            event.getChannel().type();


           String URL = "https://api.pushshift.io/reddit/search/submission/?q" + query + "&subreddit=" + subreddit + "&sort=desc&size=50&sort_type=score";
//            URL[1] = "https://api.pushshift.io/reddit/search/submission/?q" + query + "&subreddit=" + subreddit + "&sort=desc&size=50&sort_type=num_comments";




            URL url = null;
            try {
                url = new URL(URL);
                URLConnection request = url.openConnection();
                request.connect();

                // Convert to a JSON object to print data
                JSONParser jp = new JSONParser(); //from gson
                JSONObject root = (JSONObject) jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JSONObject rootobj = new JSONObject(root);
                JSONArray array = (JSONArray) rootobj.get("data");


                Random rand = new Random();
                int upperbound= array.size();
                int ran = rand.nextInt(upperbound);

                JSONObject obj = (JSONObject) array.get(ran);

                String memeURL = obj.get("url").toString();
                String title = obj.get("title").toString();
                String author = obj.get("author").toString();
                String nsfw = obj.get("over_18").toString();
                String spoiler = obj.get("spoiler").toString();
                String ups = obj.get("score").toString();
                String postlink = obj.get("full_link").toString();

                if (nsfw.equalsIgnoreCase("true")) {
                    if (event.getChannel().asServerTextChannel().get().isNsfw()) {
                        new MessageBuilder().setEmbeds(new EmbedBuilder()
                                .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif", "https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                                .setImage(memeURL)
                                .setTitle(title)
                                .setDescription("[Posted](" + postlink + ") *by u/" + author + " on r/" + subreddit + "*")
                                .setFooter("Upvotes: " + ups + "   NSFW: " + nsfw + "    SPOILER: " + spoiler+ "\nResponse time: " +(System.currentTimeMillis()-start) + "ms")
                        ).send(event.getChannel());
                    } else {
                        event.getChannel().sendMessage("The post contains nsfw\nTo get the post send command in the nsfw channel");
                    }
                } else {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif", "https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                            .setImage(memeURL)
                            .setTitle(title)
                            .setDescription("[Posted](" + postlink + ") *by u/" + author + " on r/" + subreddit + "*")
                            .setFooter("Upvotes: " + ups + "   NSFW: " + nsfw + "    SPOILER: " + spoiler+ "\nResponse time: " +(System.currentTimeMillis()-start) + "ms")
                    ).send(event.getChannel());
                }








            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }


        }
    }
}
