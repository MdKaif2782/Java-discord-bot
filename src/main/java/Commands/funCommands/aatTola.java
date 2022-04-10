package Commands.funCommands;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class aatTola implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        long farhanerID = event.getMessageAuthor().getId();
        long mentionID = 0;
        for (User mentionedUser : event.getMessage().getMentionedUsers()) {
            mentionID = mentionedUser.getId();
        }


        System.out.println("farhaner id : "+ farhanerID);
        System.out.println("mentioned id: " + mentionID);

        if (farhanerID == Long.parseLong("461021332491075585") || mentionID==Long.parseLong("461021332491075585")){
            System.out.println("number matched");
            event.getMessage().addReaction("\uD83C\uDFE2");
        } else {
            System.out.println("didnt");
        }

    }
}
