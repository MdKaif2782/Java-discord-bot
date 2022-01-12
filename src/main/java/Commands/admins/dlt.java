package Commands.admins;


import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class dlt implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String[] msg = event.getMessageContent().split(" ");
        int limit = Integer.parseInt(msg[1]);

        if (msg[0].equalsIgnoreCase("!clear")) {
            if (event.getMessageAuthor().canDeleteMessage() || event.getMessageAuthor().isBotOwner()) {
                CompletableFuture<MessageSet> message = event.getChannel().getMessages(limit);

                try {
                    event.getChannel().deleteMessages(message.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setDescription("**" + limit + " messages have been deleted! ** :white_check_mark:")
                        .setColor(Color.RED)).send(event.getChannel());
            }
        }
    }
}
