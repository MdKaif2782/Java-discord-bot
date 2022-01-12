package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class gay implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String msg = event.getMessageContent().toLowerCase();



            if (msg.contains("gay")
            || msg.contains("gay.")
                    || msg.contains("gay!")
                    || msg.contains("gay?")
                    || msg.contains("gay,")
                    || msg.contains("gay/.")
                    || msg.contains("geh.")
                    || msg.contains("geh!")
                    || msg.contains("geh?")
                    || msg.contains("geh,")
                    || msg.contains("geh/.")
                    || msg.contains("geh")
                    || msg.contains("gae.")
                    || msg.contains("gae!")
                    || msg.contains("gae?")
                    || msg.contains("gae,")
                    || msg.contains("gae/.")
                    || msg.contains("gae")

            ) {
                event.addReactionToMessage("\uD83D\uDE03");
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setTitle("GAY?")
                        .setDescription("Yes this guy :point_down:")
                        .setImage("https://cdn.discordapp.com/attachments/924564718780293122/927156709644980274/received_425974435397080.jpeg")
                        .setColor(Color.PINK)).send(event.getChannel());
            }

            }
        }


