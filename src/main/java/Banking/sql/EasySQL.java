package Banking.sql;

import java.sql.*;

public class EasySQL {
        String ServerID,UserID;
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://castor.db.elephantsql.com:5432/nowgtsdo",
                "nowgtsdo",
                "aCTKxGuRAZx6HNJHNF2HFDY2GUAp-ydO");

        public EasySQL(String serverID, String userID) throws SQLException {
            this.UserID = userID;
            this.ServerID = serverID;
        }

       public void createAccount(String UserID, String ServerID, String UserName, String ServerName) throws SQLException {
               PreparedStatement preparedStatement = conn.prepareStatement("insert into newtable2(userid,serverid,uniqueid,username,servername)" +
                       " values("+UserID +","+ServerID+","+ServerID+UserID+", '"+UserName+"' , '"+ServerName+ "')");
               preparedStatement.execute();

        }
        public void valueIncrement(int value) throws SQLException {

                PreparedStatement preparedStatement = conn.prepareStatement(
                        "update newtable2 " +
                                "set botmoney=botmoney+"+value +
                                "where serverid = '"+ServerID+"' and userid = " +"'"+UserID+"' ");
                preparedStatement.execute();
        }
       public ResultSet resultSet() throws SQLException {

                PreparedStatement preparedStatement = conn.prepareStatement("Select * from newtable2 where serverid = "+ServerID +
                        " and userid="+UserID);
                preparedStatement.execute();
               return preparedStatement.getResultSet();

        }

        public long getBalance() throws SQLException {
            long balance = 0;
            ResultSet rs = resultSet();
            while (rs.next()){
                balance = Long.parseLong(rs.getString("botmoney"));
            }
            return balance;
        }
    public long getBlackMoney() throws SQLException {
        long balance = 0;
        ResultSet rs = resultSet();
        while (rs.next()){
            balance = Long.parseLong(rs.getString("blackmoney"));
        }
        return balance;
    }
    public String getUserName() throws SQLException {
        String UserName = null;
        ResultSet rs1 = resultSet();
        while (rs1.next()){
            UserName = rs1.getString("username");
        }
        return UserName;
    }
    public String getServerName() throws SQLException {
        String ServerName = null;
        ResultSet rs2 = resultSet();
        while (rs2.next()){
            ServerName = rs2.getString("servername");
        }
        return ServerName;
    }

       public ResultSet resultSet(String SQL) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                preparedStatement.execute();
                return preparedStatement.getResultSet();
        }
    public boolean hasAccount() throws SQLException {
        boolean value = true;
        ResultSet rs = resultSet();
        return rs.next();
    }
        public Connection connection(){
                return conn;
        }



}
