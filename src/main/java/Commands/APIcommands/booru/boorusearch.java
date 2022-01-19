package Commands.APIcommands.booru;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

public class boorusearch implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msgg = event.getMessageContent().split(" ");

        if (msgg[0].equalsIgnoreCase("!booru")) {

            event.getChannel().type();

            String tags = msgg[1]+" ";

            {

                if (!msgg[msgg.length-1].contains("getall")) {

                    for (int i = 2; i < msgg.length; i++) {
                        tags = tags + msgg[i] + " ";
                    }
                } else {
                    for (int i = 2; i < msgg.length-1; i++) {
                        tags = tags + msgg[i] + " ";
                    }
                }

//                String tags[] = taggo.split(" ");


//
//                for (int i = 0; i < taggo.length(); i++) {
//                    event.getChannel().sendMessage(tags[i]); }

//                event.getChannel().sendMessage(tags);


                int loopcount = 3;

                String[] message = {};


                message = tags.split(" ");
                loopcount = message.length;


                System.out.println(loopcount);

//        String[] mainTags = new String[loopcount];

                ArrayList<String> TAGS = new ArrayList<>();


                for (int j = 0; j < loopcount; j++) {

                    if (loopcount > 2) {
                        event.getChannel().sendMessage("You cannot search for more than 2 tags at a time");
                        break;
                    }

                    String[] msg = {};

                    String tag;
                    if (message[j].contains("_")) {
                        msg = message[j].split("_");
                        tag = msg[0];
                    } else {
                        tag = message[j];
                    }

                    URL url = null;
                    try {
                        url = new URL("https://danbooru.donmai.us/wiki_pages.json?commit=Search&search%5Border%5D=post_count&search%5Btitle_or_body_matches%5D=" + tag + "&limit=100");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    URLConnection request = null;
                    try {
                        request = url.openConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        request.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Convert to a JSON object to print data
                    JSONParser jp = new JSONParser(); //from gson
                    JSONArray root = null; //Convert the input stream to a json element
                    try {
                        root = (JSONArray) jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
//        JSONObject rootobj = new JSONObject(roots);
//        JSONArray array = (JSONArray) rootobj.get("data");


                    ArrayList<String> MatchingTitles = new ArrayList<>();

                    for (int i = 0; i < root.size(); i++) {
                        JSONObject items = (JSONObject) root.get(i);
                        String title = items.get("title").toString();

//            System.out.println(title);


                        if (!message[j].contains("_")) {
                            if (title.contains(message[j])) {
                                MatchingTitles.add(title);
                            }
                        }
                        if (message[j].contains("_")) {
                            String[] part = message[j].split("_");
                            if (title.contains(part[0])) {
                                if (title.contains(part[1])) {
                                    MatchingTitles.add(title);
                                }
                            }
                        }
                    }
                    if (MatchingTitles.size() > 0) {

//                mainTags[j] = MatchingTitles.get(0);

                        TAGS.add(MatchingTitles.get(0));

                        for (int i = 0; i < MatchingTitles.size(); i++) {
                            System.out.println(MatchingTitles.get(i));
                        }

                        System.out.println("\n\n\n\n\n");


//        System.out.println(roots);
                    } else {
                        event.getChannel().sendMessage("result not found for tag: " + message[j]);
                        System.out.println("result not found for tag: " + message[j]);
                    }


                    for (int i = 0; i < TAGS.size(); i++) {
                        System.out.println(TAGS.get(i));
                    }

                }

                String postURL = "https://danbooru.donmai.us/posts.json?limit=200&tags=";

                for (int i = 0; i < TAGS.size(); i++) {

                    if (TAGS.get(i).contains("(")) {
                        String replace = TAGS.get(i).replace("(", "%28");
                        String replace2 = replace.replace(")", "%29");

                        if (TAGS.get(i).contains("'")) {
                            String replace3 = replace2.replace("'", "%27");
                            postURL = postURL + replace3 + "+";
                        } else {
                            postURL = postURL + replace2 + "+";
                        }


                    } else {
                        postURL = postURL + TAGS.get(i) + "+";
                    }


                }

                System.out.println(postURL);

                //Finally parsing the posts uwu

                URL urll = null;
                try {
                    urll = new URL(postURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                URLConnection requests = null;
                try {
                    requests = urll.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    requests.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONParser jpp = new JSONParser(); //from gson
                JSONArray roots = null;
                try {
                    roots = (JSONArray) jpp.parse(new InputStreamReader((InputStream) requests.getContent()));
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }


                //post no.
                if (roots.size() == 0 || postURL.equalsIgnoreCase("https://danbooru.donmai.us/posts.json?limit=200&tags=")) {
                    event.getChannel().sendMessage("No result found");
                    System.out.println("No result found");
                } else {

                    if (!msgg[msgg.length - 1].equalsIgnoreCase("getall")) {

                        Random rand = new Random();
                        int upperbound = roots.size();
                        int ran = rand.nextInt(upperbound);

                        JSONObject postNo = (JSONObject) roots.get(ran);

                        String resultsfound = String.valueOf(roots.size());

                        String imagelink;
                        if (postNo.containsKey("large_file_url")) {

                             imagelink = postNo.get("large_file_url").toString();
                        } else {
                            imagelink = postNo.get("source").toString();
                        }
                        String relatedTags = postNo.get("tag_string").toString();
                        String charTag = postNo.get("tag_string_character").toString();
                        String seriesTag = postNo.get("tag_string_copyright").toString();

//                    System.out.println(imagelink);
//                    System.out.println("Related Tags: " + relatedTags);
//                    System.out.println("Character Tag: " + charTag);
//                    System.out.println("Source Tag: " + seriesTag);
//                    System.out.println(resultsfound + " results found");

                        new MessageBuilder().setEmbeds(new EmbedBuilder()
                                .setImage(imagelink)
                                .setFooter("\nCharacter Tags: " + charTag +
                                        "\nSource Tag: " + seriesTag +
                                        "\nResults found: " + resultsfound)
                                .setColor(Color.BLACK)).send(event.getChannel());


                    } else {
                        for (int i = 0; i< roots.size();i++){
                            JSONObject postNo = (JSONObject) roots.get(i);

                            String resultsfound = String.valueOf(roots.size());

                            String imagelink;
                            if (postNo.containsKey("large_file_url")) {

                                imagelink = postNo.get("large_file_url").toString();
                            } else {
                                imagelink = postNo.get("source").toString();
                            }
                            String relatedTags = postNo.get("tag_string").toString();
                            String charTag = postNo.get("tag_string_character").toString();
                            String seriesTag = postNo.get("tag_string_copyright").toString();

//                    System.out.println(imagelink);
//                    System.out.println("Related Tags: " + relatedTags);
//                    System.out.println("Character Tag: " + charTag);
//                    System.out.println("Source Tag: " + seriesTag);
//                    System.out.println(resultsfound + " results found");

                            new MessageBuilder()
                                    .append("#" + i +" out of " + roots.size(), MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                                    .setEmbed(new EmbedBuilder()
                                            .setImage(imagelink)
                                            .setColor(Color.BLACK))
                                    .send(event.getChannel());

                        }



                    }
                }






//


            }

        }

    }
}

