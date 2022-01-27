package Banking.sql;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.math.BigDecimal;
import java.sql.*;

public class MoneyDistribute implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        long ServerID= event.getServer().get().getId();
        if (event.getMessageContent().equalsIgnoreCase("!distribute")){

            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                        "user=sql3467490&password=a7leh3zlaA");
                Statement stmt=conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("select * from MainTable"+" where UserID=924608474409742357 AND ServerID="+ServerID );

                long bankbal=0;
                long banks;
                String baank;
                BigDecimal bank = null;
                while (resultSet.next()){
                    String bankba = resultSet.getString("Money");
                     bank = resultSet.getBigDecimal("Money");

                    baank = String.valueOf(bank);
                    bankbal= Long.parseLong(baank);
                }

                System.out.println(bank);
                long minus = (long) (bankbal*0.9);
                System.out.println(minus);
                PreparedStatement statement = conn.prepareStatement("UPDATE MainTable" +
                        " SET Money=Money-"+minus+
                        " WHERE UserID=924608474409742357 AND ServerID="+ServerID);
                statement.executeUpdate();

                ResultSet resultSets = stmt.executeQuery("select * from MainTable"+" where ServerID="+ServerID );

                int i=0;
                while (resultSets.next()){
                    i++;
                }
                System.out.println(bankbal);
                System.out.println(i+"\n\n\n\n\n\n");

                long givenMoneytoEach = (minus/i);

                PreparedStatement stttm1 = conn.prepareStatement("UPDATE MainTable" +
                        " SET Money=Money+"+givenMoneytoEach+
                        " WHERE ServerID="+ServerID);
                stttm1.executeUpdate();

                event.getChannel().sendMessage(givenMoneytoEach+"$ is given to each User\nTotal Distributed $"+bankbal*0.9);

                conn.close();



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
