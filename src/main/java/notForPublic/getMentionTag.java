package notForPublic;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class getMentionTag implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        for (User mentionedUser : event.getMessage().getMentionedUsers()) {
            String men = mentionedUser.getMentionTag();
            System.out.println("Mention tag is : "+ men);
        }

    }
}
