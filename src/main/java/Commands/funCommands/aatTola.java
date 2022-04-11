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
            System.out.println("number matched faran");
            event.getMessage().addReaction("\uD83C\uDFE2");
        } else if (farhanerID == 527751947462115350l || mentionID==527751947462115350l){
            System.out.println("number matched..shoreph");
            event.getMessage().addReaction("\uD83D\uDE97");

        }else if (farhanerID == 739720998672269323l || mentionID==739720998672269323l){
            System.out.println("number matched..rakibol");
            event.getMessage().addReaction("\uD83E\uDDE0");
        }
        else {
            System.out.println("milenai");
        }

    }
}
