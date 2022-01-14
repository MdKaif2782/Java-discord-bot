package Commands.baiscCommands;

import java.awt.Color;
import java.util.Random;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;



public class Rate implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent message) {
        String[] msg = message.getMessageContent().split(" ");

        Random rand = new Random();
        int upperbound = 100;
        int ran = rand.nextInt(upperbound);


        if (msg[0].equals("!rate"))
        {
            long start = System.currentTimeMillis();
            if (msg.length>2) {

                for (User mentionedUser : message.getMessage().getMentionedUsers()) {

                    new MessageBuilder().setEmbed(new EmbedBuilder()
                            .setAuthor(mentionedUser)
                            .setTitle("Rate Calculator")
                            .setDescription(mentionedUser.getMentionTag() + " you are " + ran + "% " + msg[1])
                            .setColor(Color.PINK)
                            .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
                            .setThumbnail("https://forums.ccbluex.net/assets/uploads/profile/1650-profileavatar.jpeg")
                    ).send(message.getChannel());
                }


            }
            else {
                new MessageBuilder().setEmbed(new EmbedBuilder()
                        .setAuthor(message.getMessageAuthor())
                        .setTitle("Rate Calculator")
                        .setDescription(message.getMessageAuthor().getDisplayName() + " you are " + ran + "% " + msg[1])
                        .setColor(Color.PINK)
                        .setFooter("Response time: " +(System.currentTimeMillis()-start) + "ms")
                        .setThumbnail("https://forums.ccbluex.net/assets/uploads/profile/1650-profileavatar.jpeg")
                ).send(message.getChannel());
            }
        }



        }
    }

