package Banking;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class BankHelp implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("!bank")) {

            new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setAuthor("JavaCord","https://www.youtube.com/watch?v=dQw4w9WgXcQ","https://cdn.discordapp.com/avatars/924608474409742357/55ab8b4aadfad524d40bb37664c46b93.png")
                    .setTitle("Bank Aqua")
                            .setThumbnail("https://c.tenor.com/8zfn8bToJ4oAAAAM/mr-mc-mahon-ggez-moneh-mrmcmahon.gif")
                            .addField("About","A newly created bank created by noob Bot dev <@612922259014680576> .Currently there is only one way to add money to your bank and that is sending more messages :) Yuh that's it. For each message you will receive $3.lame right?\n More features are coming soon like Trading,Gambling,Buying and selling items etc")
                            .addField("Commands", "1. `!bal` -> To check your bank balance\n" +
                                    "2. `!rank` -> To get Leaderboard of the richest person")
                    .setColor(Color.LIGHT_GRAY)).send(event.getChannel());

        }
    }
}
