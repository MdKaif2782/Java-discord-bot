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
import java.io.FileWriter;
import java.io.IOException;


public class BalanceAdd implements MessageCreateListener {




    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ServerID = String.valueOf(event.getServer().get().getId());

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src\\main\\resources\\bankk.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject users = (JSONObject) jsonObject.get(ServerID);


            String name = String.valueOf(event.getMessageAuthor().getId());
            if (users.containsKey(name)) {

                long prevBal = (long) users.get(name);
                prevBal = prevBal + 3;
                users.replace(name, prevBal);

                FileWriter file = new FileWriter("src\\main\\resources\\bankk.json", false);
                file.write(jsonObject.toJSONString());
                file.flush();


                if (event.getMessageContent().equalsIgnoreCase("!bal")) {

//                    event.getChannel().sendMessage("Your balance is: $" + prevBal+ "\nBank ID:"+name );
                    new MessageBuilder().setEmbeds(new EmbedBuilder()

                            .setAuthor("Bot Bank", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                            .setTitle("Balance check")
                            .setThumbnail("https://c.tenor.com/-1phYTnql_kAAAAC/xrd-exrd.gif")
                            .setDescription(event.getMessageAuthor().getDisplayName()+" your account balance is: **$" + prevBal +"**")

                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
            }
//            else {
//                jsonObject.put(name, 0);
//
//                FileWriter file = new FileWriter("src\\main\\resources\\bankk.json", false);
//                file.write(jsonObject.toJSONString());
//                file.flush();
//            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
