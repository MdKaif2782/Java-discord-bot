package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class nosimp implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String msg = event.getMessageContent().toLowerCase();

        if (msg.contains("i love you")
                || msg.contains("ily")
                || msg.contains("i like u")
                || msg.contains("i like you")
                || msg.contains("i love u")
                || msg.contains("you are cute")
                || msg.contains("u r q8")
                || msg.contains("u r cute"))
        {
            event.getMessage().delete("No simping");
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setTitle("No spimping")
                    .setImage("https://i.ibb.co/VQQKDhj/Screenshot-2022-01-02-162218.png")
                    .setDescription(event.getMessageAuthor().getDisplayName() + " u fucking simp!".toUpperCase())
                    .setColor(Color.RED)).send(event.getChannel());

        }
    }
}
