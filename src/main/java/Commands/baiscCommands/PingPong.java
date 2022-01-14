package Commands.baiscCommands;


import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class PingPong implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent message) {
        if (message.getMessageContent().equalsIgnoreCase("!ping"))
        {
            long start = System.nanoTime();

            message.getChannel().sendMessage("Pong!\n"+(System.nanoTime()-start)/1000f+"ms");
        }
    }
}
