package Commands.admins;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class msgdelete implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String a;
        String[] msg =  event.getMessageContent().split(" ");

        if (msg[0].equalsIgnoreCase("!avatar")) {

            if (msg.length > 1) {

                for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                    a = String.valueOf(mentionedUser.getAvatar(1024).getUrl());
                    event.getChannel().sendMessage(a);
                }
            } else {
                a = String.valueOf(event.getMessageAuthor().getAvatar().getUrl());
                event.getChannel().sendMessage(a);
            }
        }
    }
}
