package Banking;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class leadboard implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("!rank")) {
            String ServerID = String.valueOf(event.getServer().get().getId());
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader("bankk.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject users = (JSONObject) jsonObject.get(ServerID);
                String json = users.toJSONString();

                int range = users.size();

                String[] set = new String[range];
                String[] set2 =new String[range];

                for (int i=0; i<range; i++ ) {
                    String[] t0 = json.split("}");
                    String[] t = t0[0].split(",");
                    String[] t1 = t[i].split(":");
                    String f = t1[1];
                    set[i] = f;

                    String[] s1= t1[0].split("\"");
                    set2[i] = s1[1];

                }
                long [] arr = new long [range];
                for(int i=0; i< range; i++) {
                    arr[i] = Long.parseLong(set[i]);
                }
                long temp;
                String temp2;
                for (int i=0; i<range; i++) {
                    for (int j=i; j< range;j++) {
                        if (arr[i]<arr[j]) {
                            temp2 = set2[i];
                            set2[i] = set2[j];
                            set2[j] = temp2;

                            temp = arr[i];
                            arr[i] = arr[j];
                            arr[j] = temp;



                        }



                    }
                }

                // arr -- value
                // set2 --- key

                System.out.println(Arrays.toString(arr));
                //event.getChannel().sendMessage(Arrays.toString(arr));
                System.out.println(Arrays.toString(set2));



                String server0[] = event.getServer().toString().split("]");
                String server00[] = server0[0].split("\\[");
                String server = server00[1];

                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor("Bot Bank", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                        .setTitle("Richest users in " + server + ":")
                        .setDescription(":first_place: **$" +arr[0] + "** - <@" + set2[0]  +  ">\n\n" +
                                ":second_place: **$" +arr[1] + "** - <@" + set2[1]  +  ">\n\n" +
                                ":third_place: **$" +arr[2] + "** - <@" + set2[2]  +  ">\n\n" )
                                .setThumbnail("https://c.tenor.com/8zfn8bToJ4oAAAAM/mr-mc-mahon-ggez-moneh-mrmcmahon.gif")
                        .setColor(Color.LIGHT_GRAY)).send(event.getChannel());


            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }


        }
    }
}
