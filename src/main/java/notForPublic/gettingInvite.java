package notForPublic;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.ServerJoinListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class gettingInvite implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {


    }
}

