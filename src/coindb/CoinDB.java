/*
 * Dawn Myers
 * ITDEV 140 Mequon 2016
 * Week 12 Activity 5
 */
package coindb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Dawn
 */
public class CoinDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
   {   
       if(args.length == 0)
       {
           System.out.println("Need database properties for application to run");
           return;
       }
       
       CoinDataSource.init(args[0]);
       Connection conn = CoinDataSource.getConnection();
       try
       {
           Statement stat = conn.createStatement();
           try
           {
               stat.execute("CREATE TABLE Coins (Name VARCHAR(20), Value INTEGER)");
           }
           catch(Exception e)
           {
               System.out.println(e);
           }
           stat.execute("INSERT INTO Coins VALUES ('Penny', 1)");
           stat.execute("INSERT INTO Coins VALUES ('Nickel', 5)");
           stat.execute("INSERT INTO Coins VALUES ('Dime', 10)");
           stat.execute("INSERT INTO Coins VALUES ('Quarter', 25)");
           stat.execute("INSERT INTO Coins VALUES ('Half Dollar', 50)");
           stat.execute("INSERT INTO Coins VALUES ('Dollar', 100)");
           
           String query = "SELECT SUM(Value) AS SUM_VALUE FROM Coins";
           ResultSet valueResult = stat.executeQuery(query);
           while(valueResult.next())
           {
               int sumOfCoins = valueResult.getInt("SUM_VALUE");
               System.out.println("The sum of all the coins is " + sumOfCoins);
           }
           stat.execute("DROP TABLE Coins");
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       finally
       {
           conn.close();
       }
   }
    
}
