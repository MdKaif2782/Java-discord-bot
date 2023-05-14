package Commands.Tashdid;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class TashdidKalaImage implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String msg = event.getMessageContent();
        if (msg.toLowerCase().contains("tashdid er chobi")){
            String imageLink = "https://www.rollingstone.com/wp-content/uploads/2022/10/Get-Out.jpg?w=1581&h=1054&crop=1";
            event.getChannel().sendMessage(imageLink);
        }
    }
}
