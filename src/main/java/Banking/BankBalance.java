package Banking;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class BankBalance implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String[] msg = event.getMessageContent().split(" ");




        if (msg[0].equalsIgnoreCase("!Json") && msg[1].equalsIgnoreCase("balance"))
        {


            String name = String.valueOf(event.getMessageAuthor().getId());

            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader("bankk.json"));
            } catch (IOException | ParseException e) {
                event.getChannel().sendMessage("IOException / Parse Error");
                e.printStackTrace();
            }
            JSONObject jsonObject =  (JSONObject) obj;

            if (jsonObject.containsKey(name)) {
                long bal = (long) jsonObject.get(name);

                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor("Bot Bank", "reddit.com/u/md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                        .setTitle("Balance check")
                        .setDescription(event.getMessageAuthor().getDisplayName()+" your account balance is: **" + bal +"**")
                        .setThumbnail("https://media.giphy.com/media/13CfgbLnnAxOHS/giphy.gif")
                        .setColor(java.awt.Color.BLUE)).send(event.getChannel());

//                event.getChannel().sendMessage("Your account balance is: $" + bal);
            }
            else {
                event.getChannel().sendMessage("You are not registered.\nTo register type `!bank register`");
            }

        }



    }
}
