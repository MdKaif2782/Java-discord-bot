package Commands.Reader;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class Reader implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) 
    {
        String msg = event.getMessageContent();
        String auth = event.getMessageAuthor().getDisplayName();
        System.out.println( auth + ": " + msg);
    }
}