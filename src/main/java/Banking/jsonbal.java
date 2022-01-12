package Banking;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonbal implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String ServerID = String.valueOf(event.getServer().get().getId());

        if (event.getMessageContent().equalsIgnoreCase("!jsbal")) {
            String name = String.valueOf(event.getMessageAuthor().getId());
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader("src\\main\\resources\\bankk.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject users = (JSONObject) jsonObject.get(ServerID);


                long bal = (long) users.get(name);
                event.getChannel().sendMessage("Your balance is "+ bal);



            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
