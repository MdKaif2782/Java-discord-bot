package Banking;


import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class bankRegister implements MessageCreateListener  {





    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String name = String.valueOf(event.getMessageAuthor().getId());
        String ServerID = String.valueOf(event.getServer().get().getId());

            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader("src\\main\\resources\\bankk.json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject users = (JSONObject) jsonObject.get(ServerID);



                if (!users.containsKey(name)) {

                    users.put(name, 0);

                    FileWriter file = new FileWriter("src\\main\\resources\\bankk.json", false);
                    file.write(jsonObject.toJSONString());
                    file.flush();
                }


            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }




        }


    }
