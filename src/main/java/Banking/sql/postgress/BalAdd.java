package Banking.sql.postgress;

import Banking.sql.EasySQL;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.sql.SQLException;

public class BalAdd implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String ServerID = String.valueOf(event.getServer().get().getId());
        String UserID = String.valueOf(event.getMessageAuthor().getId());
        try {
            EasySQL easySQL = new EasySQL(ServerID,UserID);
            easySQL.valueIncrement(3);
            easySQL.connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
