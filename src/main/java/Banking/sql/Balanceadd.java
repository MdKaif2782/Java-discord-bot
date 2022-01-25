package Banking.sql;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.sql.*;

public class Balanceadd implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String userid = String.valueOf(event.getMessageAuthor().getId());
        String serverid = String.valueOf(event.getServer().get().getId());
        String username = event.getMessageAuthor().getDiscriminatedName();
        String servername = event.getServer().get().getName();
        String uniqueid = serverid+userid;

        String UserID =  userid+ ",";
        String UserName = "'" +username + "',";
        String ServerName = "'"+servername +"',";
        String ServerID = serverid + ",";
        String UniqueID = "'"+uniqueid + "',";

        String Money;
        String User;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                    "user=sql3467490&password=a7leh3zlaA");

            Statement stmt=conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from MainTable "+" where UniqueID="+uniqueid);

          
                PreparedStatement statement = conn.prepareStatement("UPDATE MainTable " +
                        " SET Money=Money+3, TotalMoney=TotalMoney+3"+
                        " WHERE UniqueID="+uniqueid);
                statement.executeUpdate();

                while(resultSet.next()) {
                Money = resultSet.getString("Money");
                User = resultSet.getString("UserName");
                System.out.println(User +" has "+Money + "$\n");
                }

                if (event.getMessageContent().equalsIgnoreCase("!bal")){
                    event.getChannel().sendMessage("Your balance is $" + Money);
                }


            

                PreparedStatement statement = conn.prepareStatement("INSERT INTO MainTable " +
                        " VALUES (" +UniqueID+UserID+ServerID+ServerName+UserName+"0,0,0,0,0,0,0,0,0,0,0)");
                statement.executeUpdate();

            
//


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
