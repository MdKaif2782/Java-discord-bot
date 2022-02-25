package Banking.sql.postgress;

import Banking.sql.EasySQL;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.sql.SQLException;

public class AcCreate implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String ServerID = String.valueOf(event.getServer().get().getId());
        String UserID = String.valueOf(event.getMessageAuthor().getId());
        String UserName = event.getMessageAuthor().getDiscriminatedName();
        String ServerName =event.getServer().get().getName();

        try {
            EasySQL easySQL = new EasySQL(ServerID,UserID);

            if (easySQL.hasAccount()){
                System.out.println("ac have yaya");
            } else {
                System.out.println("the user ac have not lol");
                easySQL.createAccount(UserID,ServerID,UserName,ServerName);
            }
            easySQL.connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
