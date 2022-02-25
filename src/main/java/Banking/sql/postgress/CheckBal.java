package Banking.sql.postgress;

import Banking.sql.EasySQL;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.sql.SQLException;

public class CheckBal implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String message = event.getMessageContent();
        String ServerID = String.valueOf(event.getServer().get().getId());
        String UserID = String.valueOf(event.getMessageAuthor().getId());
        if (message.equalsIgnoreCase("!bal")) {
            try {
                EasySQL easySQL = new EasySQL(ServerID, UserID);
                long balance = easySQL.getBalance();
                long black = easySQL.getBlackMoney();
                 String username = easySQL.getUserName();
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor("Bot Bank", "https://www.reddit.com/user/Md_kaif", "https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                        .setTitle("Balance check")
                        .setThumbnail("https://c.tenor.com/-1phYTnql_kAAAAC/xrd-exrd.gif")
                        .setDescription(event.getMessageAuthor().getDisplayName() + " YOUR \n**L e g a l Wallet balance:** ***$" + balance + "***\n" +
                                "**BlackMoney Wallet Balance:** ***$" + black + "***")
                        .setColor(Color.YELLOW)).send(event.getChannel());
                easySQL.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
