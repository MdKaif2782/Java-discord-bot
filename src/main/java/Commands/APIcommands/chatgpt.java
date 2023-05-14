package Commands.APIcommands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class chatgpt implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String msg = event.getMessageContent();
        String[] args = msg.split(" ");
        System.out.println(args[0]);
        if (!args[0].equals("!chat")) return;
        if (args.length < 2) return;

        StringBuilder message = new StringBuilder();
        for (String arg:args){
            message.append(arg).append(" ");
        }
        String finalMessage = message.toString();
        // set status typing
        event.getChannel().type();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openai80.p.rapidapi.com/chat/completions"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "bb834ebd60mshbda2ab24352bae8p137102jsne7dad0e3970e")
                .header("X-RapidAPI-Host", "openai80.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"model\": \"gpt-3.5-turbo\",\r\n    \"messages\": [\r\n        {\r\n            \"role\": \"user\",\r\n            \"content\": \""+finalMessage+"\"\r\n        }\r\n    ]\r\n}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject json = (JSONObject) new JSONParser().parse(response.body());
            JSONArray choices = (JSONArray) json.get("choices");
            JSONObject choice = (JSONObject) choices.get(0);
            JSONObject messageObj = (JSONObject) choice.get("message");
            String res = (String) messageObj.get("content");
            event.getChannel().sendMessage(res);
        } catch (IOException | InterruptedException | ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
