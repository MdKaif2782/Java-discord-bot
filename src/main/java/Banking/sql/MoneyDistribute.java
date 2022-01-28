package Banking.sql;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.math.BigDecimal;
import java.sql.*;

public class MoneyDistribute implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        long ServerID = event.getServer().get().getId();
        if (event.getMessageContent().equalsIgnoreCase("!distribute")) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp);
            long now = timestamp.getTime();

            Connection conn = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                        "user=sql3467490&password=a7leh3zlaA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = null;
            try {
                resultSet = stmt.executeQuery("select * from MainTable" + " WHERE UserID=924608474409742357 AND ServerID=" + ServerID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            long difference = 0;
            int counter = 0;
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                BigDecimal previous = null;
                try {
                    previous = resultSet.getBigDecimal("Timeinmili");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(previous);

                long prev = Long.parseLong(String.valueOf(previous));
                difference = (now - prev) / 1000;
                try {
                    counter = resultSet.getInt("RobCount");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            long maxtime = 24*60*60;
            if (difference >= maxtime) {

                Connection connection1 = null;
                try {
                    connection1 = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                            "user=sql3467490&password=a7leh3zlaA");
                    Statement statement1 = connection1.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery("select * from MainTable" + " where UserID=924608474409742357 AND ServerID=" + ServerID);

                    long bankbal = 0;
                    long banks;
                    String baank;
                    BigDecimal bank = null;
                    while (resultSet1.next()) {
                        String bankba = resultSet1.getString("Money");
                        bank = resultSet1.getBigDecimal("Money");

                        baank = String.valueOf(bank);
                        bankbal = Long.parseLong(baank);
                    }

                    System.out.println(bank);
                    long minus = (long) (bankbal * 0.9);
                    System.out.println(minus);
                    PreparedStatement statement = connection1.prepareStatement("UPDATE MainTable" +
                            " SET Money=Money-" + minus +
                            " WHERE UserID=924608474409742357 AND ServerID=" + ServerID);
                    statement.executeUpdate();

                    ResultSet resultSets = statement1.executeQuery("select * from MainTable" + " where ServerID=" + ServerID);

                    int i = 0;
                    while (resultSets.next()) {
                        i++;
                    }
                    System.out.println(bankbal);
                    System.out.println(i + "\n\n\n\n\n\n");

                    long givenMoneytoEach = (minus / i);

                    PreparedStatement stttm1 = connection1.prepareStatement("UPDATE MainTable" +
                            " SET Money=Money+" + givenMoneytoEach +
                            " WHERE ServerID=" + ServerID);
                    stttm1.executeUpdate();

                    event.getChannel().sendMessage(givenMoneytoEach + "$ is given to each User\nTotal Distributed $" + bankbal * 0.9);

                    connection1.close();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                PreparedStatement statement1 = null;
                try {
                    statement1 = conn.prepareStatement("UPDATE MainTable" +
                            " SET Timeinmili="+now+
                            " WHERE 1" );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    statement1.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    conn.close();
                    connection1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {

                int hour = (int) ((maxtime-difference)/3600);
                int min = (int) (((maxtime-difference)/60)%60);
                int sec = (int) (((maxtime-difference)%3600)%60);

                event.getChannel().sendMessage("Wait " + hour +" hour "+min+" min " +sec+ "sec to continue");
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
