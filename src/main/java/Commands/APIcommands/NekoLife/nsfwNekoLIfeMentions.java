package Commands.APIcommands.NekoLife;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
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
import java.util.List;

public class nsfwNekoLIfeMentions implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String mention = null;
        String text = null;
        long start = System.currentTimeMillis();

        boolean nsfw = event.getChannel().asServerTextChannel().get().isNsfw();
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!n") && msg.length>2 && nsfw) {

            if (msg[1].equalsIgnoreCase("cum")
                    || msg[1].equalsIgnoreCase("Random_hentai_gif")) {

                for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                    mention = mentionedUser.getMentionTag();
                }


                if (msg[1].equalsIgnoreCase("cum")) {
                    text = "You came inside " + mention;
                } else if (msg[1].equalsIgnoreCase("Random_hentai_gif")) {
                    text = "You segsed " + mention;
                }

                String sURL = "https://nekos.life/api/v2/img/" + msg[1]; //just a string

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

                    if (rootobj.containsKey("url")) {
                        String imageURL = rootobj.get("url").toString();


                        for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                            new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setDescription(text)
                                    .setImage(imageURL)
                                    .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
                            ).send(event.getChannel());
                        }

                    } else {
                        event.getChannel().sendMessage("You entered a wrong command\nFor more information and help type ```!nhelp```");
                    }
                } catch (IOException | ParseException e) {

                    e.printStackTrace();
                }


            }
        }


        }
    }

