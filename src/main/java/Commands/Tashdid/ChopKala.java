package Commands.Tashdid;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class ChopKala implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        long tashdid = 1100302720075841557L;
        long kf = 612922259014680576L;
        if (event.getMessageAuthor().getId()==kf){
            event.getMessage().addReaction("\uD83C\uDD72");
            event.getMessage().addReaction("\uD83C\uDD77");
            event.getMessage().addReaction("\uD83C\uDD7E");
            event.getMessage().addReaction("\uD83C\uDD7F");
            event.getMessage().addReaction("\uD83C\uDD7A");
            event.getMessage().addReaction("\uD83C\uDD70");
            event.getMessage().addReaction("\uD83C\uDD7B");
            event.getMessage().addReaction("\uD83C\uDD70");
        }
    }
}
