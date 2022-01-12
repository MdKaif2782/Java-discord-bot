package Commands.APIcommands.NekoLife;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class NekoHelp implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!nhelp")) {
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                    .setTitle("API help")
                            .setDescription("uwu")
                            .addField("Command formatting","For NSFW and SFW type ```!n <tag>```\nFor Specials type ```!ns <tag>```")
                            .addField("SFW","Get tags from the below image")
                            .setImage("https://i.ibb.co/10c1Vyn/sfw.png")
                            .setFooter("N/B: Some of the tags might not available because of some errors in API Server")
                    .setColor(Color.GREEN)).send(event.getChannel());

            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                    .setTitle("API help")
                    .addField("Command formatting","For NSFW and SFW type ```!n <tag>```\nFor Specials type ```!ns <tag>```")
                    .addField("NSFW","Get tags from the below image")
                    .setImage("https://i.ibb.co/C27yhng/nsfw.png")
                    .setFooter("N/B: Some of the tags might not available because of some errors in API Server")
                    .setColor(Color.RED)).send(event.getChannel());

            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setAuthor("JavaCord", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                    .setTitle("API help")
                    .addField("Command formatting","For NSFW and SFW type ```!n <tag>```\nFor Specials type ```!ns <tag>```")
                    .addField("SPECIALS","Get tags from the below image")
                    .setImage("https://i.ibb.co/Lzp8YMy/spec.png")
                    .setFooter("N/B: Some of the tags might not available because of some errors in API Server")
                    .setColor(Color.BLACK)).send(event.getChannel());
        }
    }
}
