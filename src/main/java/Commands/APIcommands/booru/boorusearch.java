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
        String[] msg = event.getMessageContent().split(" ");
        String kaifSaif = "yes horny";


        if (msg[0].equalsIgnoreCase("!booru")) {


            event.getChannel().type();
            String message = event.getMessageContent();

            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append(message);
            if (!message.contains("getall")) {
                stringBuffer.append(" only1");
            }
            if (!message.contains("loop:")) {
                stringBuffer.append(" loop:25");
            }
            if (!message.contains("nsfw")) {
                stringBuffer.append(" sfw");
            }
            System.out.println(stringBuffer.toString());

            String newString = stringBuffer.toString();
            String[] msgg = newString.split(" ");

            for (int i =0;i<msgg.length;i++){
                System.out.println("msgg["+i+"] = "+msgg[i]);
            }
            StringBuffer taaags = new StringBuffer();

            for (int i=1;i<msgg.length-3;i++){
                taaags.append(msgg[i]);
                taaags.append(" ");
            }
            System.out.println(taaags.toString());
            String tags = taaags.toString();
            int loopno=0;
            if (!msgg[msgg.length-1].equalsIgnoreCase("only1")) {
                String[] lp = msgg[msgg.length - 2].split(":");
                loopno = Integer.parseInt(lp[1]);
            }else {
                String[] lp = msgg[msgg.length - 3].split(":");
                loopno = Integer.parseInt(lp[1]);
            }
            boolean nsfw = false;
            if(message.contains("nsfw")){
                nsfw=true;
            }
            System.out.println("Tags = "+tags);
            System.out.println("loopno -- "+loopno);
            System.out.println(nsfw);





            System.out.println(tags);

//                String tags[] = taggo.split(" ");


//
//                for (int i = 0; i < taggo.length(); i++) {
//                    event.getChannel().sendMessage(tags[i]); }

//                event.getChannel().sendMessage(tags);


                int loopcount = 3;

                String[] message1 = {};


                message1 = tags.split(" ");
                loopcount = message1.length;


                System.out.println(loopcount);

//        String[] mainTags = new String[loopcount];

                ArrayList<String> TAGS = new ArrayList<>();


                for (int j = 0; j < loopcount; j++) {

                    if (loopcount > 62) {
                        event.getChannel().sendMessage("You cannot search for more than 2 tags at a time");
                        break;
                    }

                    String[] msg1 = {};

                    String tag;
                    if (message1[j].contains("_")) {
                        msg1 = message1[j].split("_");
                        tag = msg1[0];
                    } else {
                        tag = message1[j];
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


                        if (!message1[j].contains("_")) {
                            if (title.contains(message1[j])) {
                                MatchingTitles.add(title);
                            }
                        }
                        if (message1[j].contains("_")) {
                            String[] part = message1[j].split("_");
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
                        event.getChannel().sendMessage("result not found for tag: " + message1[j]);
                        System.out.println("result not found for tag: " + message1[j]);
                    }


                    for (int i = 0; i < TAGS.size(); i++) {
                        System.out.println(TAGS.get(i));
                    }

                }

                String postURL = "https://danbooru.donmai.us/posts.json?&tags=";

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

                JSONArray array = new JSONArray();
                boolean postase = true;


                int b = loopno;


                for (int count = 0; count < b; count++) {
                    URL urll = null;
                    try {
                        urll = new URL(postURL + "&page=" + (1 + count));
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
                    if (roots.size() == 0 || postURL.equalsIgnoreCase("https://danbooru.donmai.us/posts.json?tags=")) {

                        System.out.println("No result found");
                        if (array.size() == 0) {
                            event.getChannel().sendMessage("No result found");
                            postase = false;
                        }
                        break;
                    } else {

                        for (int c = 0; c < roots.size(); c++) {


                            JSONObject postNo = (JSONObject) roots.get(c);
                            if (postNo.containsKey("large_file_url")) {
                                array.add(roots.get(c));
                            }

                        }


//
                    }


//


                }
            ArrayList<String> sfwlinks = new ArrayList<>();
            ArrayList<String> nsfwlinks = new ArrayList<>();

                if (postase) {
                    if (!event.getMessageContent().contains("getall")) {

                        for (int i = 0; i < array.size(); i++) {

                            Random rand = new Random();
                            int upperbound = array.size();
                            int ran = rand.nextInt(upperbound);

                            JSONObject postNo = (JSONObject) array.get(ran);

                            String resultsfound = String.valueOf(array.size());

                            String imagelink;
                            if (postNo.containsKey("large_file_url")) {

                                imagelink = postNo.get("large_file_url").toString();

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
                                    break;

                                }

                        }
                    } else {
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject postNo = (JSONObject) array.get(i);

                            String resultsfound = String.valueOf(array.size());

                            String imagelink;
                            if (postNo.containsKey("large_file_url")) {

                                imagelink = postNo.get("large_file_url").toString();
                                String nsfw1 = postNo.get("rating").toString();

                                String relatedTags = postNo.get("tag_string").toString();
                                String charTag = postNo.get("tag_string_character").toString();
                                String seriesTag = postNo.get("tag_string_copyright").toString();
                                if (nsfw1.equalsIgnoreCase("q") || nsfw1.equalsIgnoreCase("e")) {
                                    nsfwlinks.add(imagelink);
                                } else {
                                    sfwlinks.add(imagelink);
                                }
                                if (!nsfw) {
                                    new MessageBuilder()
                                            .append("#" + (i + 1) + " out of " + array.size(), MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                                            .setEmbed(new EmbedBuilder()
                                                    .setImage(imagelink)
                                                    .setColor(Color.BLACK))
                                            .send(event.getChannel());
                                }


                            }


                        }
                        if (nsfw && message.contains("getall")) {
                            for (int j = 0; j < nsfwlinks.size(); j++) {
                                new MessageBuilder()
                                        .append("#" + (j + 1) + " out of " + nsfwlinks.size(), MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                                        .setEmbed(new EmbedBuilder()
                                                .setImage(nsfwlinks.get(j))
                                                .setColor(Color.BLACK))
                                        .send(event.getChannel());
                            }

                        }
                    }
                }


            }
        }
    }


