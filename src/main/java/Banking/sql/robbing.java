package Banking.sql;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.sql.*;
import java.util.Random;

public class robbing implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!rob") && msg.length<3){

            long victimUserID = event.getMessage().getMentionedUsers().get(0).getId();
            long robbberID = event.getMessageAuthor().getId();
            long ServerID = event.getServer().get().getId();

            Random random = new Random();
            int ran = random.nextInt(100);

            if (ran<50){

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                            "user=sql3467490&password=a7leh3zlaA");
                    Statement stmt=conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery("select * from MainTable "+" where ServerID="+ServerID+" AND UserID="+victimUserID);

                    String moneyofvictim = "0";
                    while (resultSet.next()){
                        moneyofvictim = resultSet.getString("Money");
                    }
                    long moneyV = Long.parseLong(moneyofvictim);

                    if (moneyV>100){
                        long newMoneyofVictim = (long) (moneyV-moneyV*0.10);
                        long addedbalancetorobber = (long) (moneyV*0.10);

                        PreparedStatement statement = conn.prepareStatement("UPDATE MainTable " +
                                " SET Money=Money-"+addedbalancetorobber+ ", TotalMoney=TotalMoney-"+addedbalancetorobber+
                                " WHERE ServerID="+ServerID +" AND UserID="+victimUserID);
                        statement.executeUpdate();

                        PreparedStatement statement2 = conn.prepareStatement("UPDATE MainTable " +
                                " SET Money=Money+"+addedbalancetorobber+", TotalMoney=TotalMoney+"+addedbalancetorobber+
                                " WHERE ServerID="+ServerID +" AND UserID="+robbberID);
                        statement2.executeUpdate();


                        event.getChannel().sendMessage("You robbed " + addedbalancetorobber+"$ from"+ event.getMessage().getMentionedUsers().get(0).getMentionTag());


                    } else {
                        event.getChannel().sendMessage("you cant rob from poors");
                    }

                    conn.close();




                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                            "user=sql3467490&password=a7leh3zlaA");
                    Statement stmt=conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery("select * from MainTable "+" where ServerID="+ServerID+" AND UserID="+robbberID);
                    String robbermoney = "0";
                    while (resultSet.next()){
                        robbermoney = resultSet.getString("Money");
                    }
                    long robberM = Long.parseLong(robbermoney);
                    long policefine = (long) (robberM*0.12);

                    PreparedStatement statement = conn.prepareStatement("UPDATE MainTable " +
                            " SET Money=Money-"+policefine+ ", TotalMoney=TotalMoney-"+policefine+
                            " WHERE ServerID="+ServerID +" AND UserID="+robbberID);
                    statement.executeUpdate();

                    conn.close();

                    event.getChannel().sendMessage("You got cought by police while robbing "+ event.getMessage().getMentionedUsers().get(0).getMentionTag() +"" +
                            "\nYou had to bribe police "+policefine+"$ to escape\n" +
                            "What a pathetic looser you are hghghghghghg");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
