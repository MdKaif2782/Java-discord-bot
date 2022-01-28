package Banking.sql;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.sql.*;

public class Balanceadd implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String userid = String.valueOf(event.getMessageAuthor().getId());
        String serverid = String.valueOf(event.getServer().get().getId());
        String username = event.getMessageAuthor().getDiscriminatedName();
        String servername = event.getServer().get().getName();
        String uniqueid = serverid + userid;

        String UserID = userid + ",";
        String UserName = "'" + username + "',";
        String ServerName = "'" + servername + "',";
        String ServerID = serverid + ",";
        String UniqueID = "'" + uniqueid + "',";

        String Money = null;
        String User;
        if (event.getMessageAuthor().isUser() || event.getMessageAuthor().isYourself()) {
            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                        "user=sql3467490&password=a7leh3zlaA");

                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("select * from MainTable " + " where UniqueID=" + uniqueid + " AND UserID=" + userid);


                PreparedStatement statement = conn.prepareStatement("UPDATE MainTable " +
                        " SET Money=Money+3, TotalMoney=TotalMoney+3" +
                        " WHERE UniqueID=" + uniqueid + " AND UserID=" + userid);
                statement.executeUpdate();

                Money = "yaya";
                String black="yaya";
                while (resultSet.next()) {
                    Money = resultSet.getString("Money");
                    User = resultSet.getString("UserName");
                    black = resultSet.getString("BlackMoney");
                    System.out.println(User + " has " + Money + "$\n\n\n\n\n\n\n\n\n");

                }
                if (event.getMessageContent().equalsIgnoreCase("!bal")) {

                    new MessageBuilder().setEmbeds(new EmbedBuilder()

                            .setAuthor("Bot Bank", "https://www.reddit.com/user/Md_kaif","https://i.pinimg.com/564x/78/a9/23/78a923b6e08e58697467007bfdd37745.jpg")
                            .setTitle("Balance check")
                            .setThumbnail("https://c.tenor.com/-1phYTnql_kAAAAC/xrd-exrd.gif")
                            .setDescription(event.getMessageAuthor().getDisplayName()+" YOUR \n**Legal Wallet balance:** ***$" + Money +"***\n" +
                                    "**BlackMoney Wallet Balance:** ***$"+black+"***")

                            .setColor(Color.YELLOW)).send(event.getChannel());

 //                   event.getChannel().sendMessage("Your balance is $" + Money);
                }

                if (Money.equalsIgnoreCase("yaya")) {
                    PreparedStatement statementt = conn.prepareStatement("INSERT INTO MainTable " +
                            " VALUES (" + UniqueID + UserID + ServerID + ServerName + UserName + "0,0,0,0,0,0,0,0,0,0,0,0,0)");
                    statementt.executeUpdate();
                }
                conn.close();


            } catch (SQLException e) {

                e.printStackTrace();
            }


        }
    }
}
