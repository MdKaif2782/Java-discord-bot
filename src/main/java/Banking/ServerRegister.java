package Banking;

import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerRegister implements ServerJoinListener {
    @Override
    public void onServerJoin(ServerJoinEvent event) {

            long ID = event.getServer().getId();
            String serverID = String.valueOf(ID);

        System.out.println("Joined server ID: " + serverID);


            //Registering id
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader("bankk.json"));
                JSONObject ServerID =  (JSONObject) obj;

                JSONObject usersID = new JSONObject();

                usersID.put("default",69);
                ServerID.put(serverID,usersID);

                FileWriter file = new FileWriter("bankk.json", false);
                file.write(ServerID.toJSONString());
                file.flush();

                System.out.println("Server Registered successfully");


            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }


    }



    }

