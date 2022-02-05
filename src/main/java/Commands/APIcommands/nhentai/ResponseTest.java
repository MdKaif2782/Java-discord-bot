package Commands.APIcommands.nhentai;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ResponseTest implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        AtomicReference<AtomicInteger> i = new AtomicReference<>(new AtomicInteger());
        if (event.getMessageContent().equalsIgnoreCase("!testresponse")) {
            event.getChannel().sendMessage("Testing...").thenAccept(message -> {
                message.addReaction("\uD83D\uDC48");
                message.addReaction("\uD83D\uDC49");


                // Attach a listener directly to the message

                message.addReactionAddListener(reactionEvent -> {
                    int count = 0;
                        if (reactionEvent.getEmoji().equalsEmoji("\uD83D\uDC49")) {
                            i.get().getAndIncrement();
                            reactionEvent.editMessage("Counting -- " + i);
                            System.out.println(reactionEvent.getCount().get());


                        }

                    });



                message.addReactionRemoveListener(reactionremove -> {
                   if (reactionremove.getEmoji().equalsEmoji("\uD83D\uDC49")){
                       int count  = reactionremove.getCount().get();
                       if (count<2){
                           i.get().getAndIncrement();
                           reactionremove.editMessage("Counting -- " + i);




                       }
                   }
                });

                message.addReactionAddListener(reactionEvent -> {
                    int count = 0;
                    if (reactionEvent.getEmoji().equalsEmoji("\uD83D\uDC48")) {
                        i.get().getAndDecrement();
                        reactionEvent.editMessage("Counting -- " + i);
                        System.out.println(reactionEvent.getCount().get());


                    }

                });

                message.addReactionRemoveListener(reactionremove -> {
                    if (reactionremove.getEmoji().equalsEmoji("\uD83D\uDC48")){
                        int count  = reactionremove.getCount().get();
                        if (count<2){
                            i.get().getAndDecrement();
                            reactionremove.editMessage("Counting -- " + i);




                        }
                    }
                });



                });


        }
    }
}
