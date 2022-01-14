package Commands.APIcommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class armpitApi implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!armpit")) {

            boolean Nsfw = event.getChannel().asServerTextChannel().get().isNsfw();

            if (Nsfw) {
                long start = System.currentTimeMillis();

                String sURL = "https://meme-api.herokuapp.com/gimme/animearmpits/"; //just a string

                // Connect to the URL using java's native library
                URL url = null;
                try {
                    url = new URL(sURL);
                    URLConnection request = url.openConnection();
                    request.connect();

                    // Convert to a JSON object to print data
                    JSONParser jp = new JSONParser(); //from gson
                    JSONObject root = (JSONObject) jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                    JSONObject rootobj = new JSONObject(root);

                    //elements
                    String memeURL = rootobj.get("url").toString();
                    String title = rootobj.get("title").toString();
                    String author = rootobj.get("author").toString();
                    String subreddit = rootobj.get("subreddit").toString();
                    String nsfw = rootobj.get("nsfw").toString();
                    String spoiler = rootobj.get("spoiler").toString();
                    String ups = rootobj.get("ups").toString();
                    String postlink = rootobj.get("postLink").toString();

                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif", "https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                            .setImage(memeURL)
                            .setTitle(title)
                            .setDescription("[Posted](" + postlink + ") *by u/" + author + " on r/" + subreddit + "*")
                            .setFooter("Upvotes: " + ups + "   NSFW: " + nsfw + "    SPOILER: " + spoiler)
                            .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")).send(event.getChannel());


                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

            }
            else {
                event.getChannel().sendMessage("This is not an NSFW channel");
            }
        }
    }
}
