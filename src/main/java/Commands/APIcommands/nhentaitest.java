package Commands.APIcommands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class nhentaitest implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("testreact")) {
            event.getChannel().sendMessage("testing");
            event.getMessage().addReaction("\uD83D\uDE00");

            if (event.getMessage().getReactions().get(0).equals("\uD83D\uDE00")) {
                event.getMessage().edit("it worked");
            }
        }
    }
}