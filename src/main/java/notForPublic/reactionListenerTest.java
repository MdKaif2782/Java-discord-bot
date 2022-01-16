package notForPublic;

import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class reactionListenerTest implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("!testm")) {
            CompletableFuture<MessageSet> msg = event.getChannel().getMessages(1);


            int a = 0;
            try {
                a = msg.get().hashCode();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage(String.valueOf(a));

        }
    }
}