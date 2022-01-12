package Commands.baiscCommands;


import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class PingPong implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent message) {
        if (message.getMessageContent().equalsIgnoreCase("!ping"))
        {
            message.getChannel().sendMessage("Pong!");
        }
    }
}
