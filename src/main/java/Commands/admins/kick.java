package Commands.admins;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class kick implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");

        if (msg[0].equalsIgnoreCase("!admin")) {
            if (event.getMessageAuthor().canBanUsersFromServer() || event.getMessageAuthor().isBotOwner()) {
                for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                    if (msg[1].equalsIgnoreCase("ban")) {
                        event.getServer().get().banUser(mentionedUser);
                    } else if(msg[1].equalsIgnoreCase("unban")) {
                        event.getServer().get().unbanUser(mentionedUser);
                    }else if(msg[1].equalsIgnoreCase("kick")) {
                        event.getServer().get().kickUser(mentionedUser);
                    }
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                            .setDescription("**" + mentionedUser.getDiscriminatedName() + " has been " + msg[1]+ "ed** :white_check_mark:")
                            .setColor(Color.RED)).send(event.getChannel());
                }

            } else {
                event.getChannel().sendMessage("You are not an Admin");
            }
        }
    }
}
