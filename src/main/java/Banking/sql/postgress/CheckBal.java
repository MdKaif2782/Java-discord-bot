package Banking.sql.postgress;

import Banking.sql.EasySQL;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.sql.SQLException;

public class CheckBal implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String message = event.getMessageContent();
        String ServerID = String.valueOf(event.getServer().get().getId());
        String UserID = String.valueOf(event.getMessageAuthor().getId());
        if (message.equalsIgnoreCase("!bal")){
            try {
                EasySQL easySQL = new EasySQL(ServerID,UserID);
                long balance  = easySQL.getBalance();
                String username = easySQL.getUserName();
                event.getChannel().sendMessage(username +" you have $"+balance);
                easySQL.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
