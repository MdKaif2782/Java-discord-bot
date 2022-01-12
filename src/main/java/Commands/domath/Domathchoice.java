package Commands.domath;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Domathchoice
{
    public class choice implements MessageCreateListener{

        @Override
        public void onMessageCreate(MessageCreateEvent event) {
            if (event.getMessageContent().equalsIgnoreCase("1"))
            {
                event.getChannel().sendMessage("Enter the first number");
            }
        }
    }
}
