import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class test{
    public static void main(String[] args) throws SQLException {
    Connection conn=null;
    try
        {
            conn= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/guests","root","root");// Guests is the schema name
            Statement stmt=conn.createStatement();
            Statement st1= conn.createStatement();
            String query = "INSERT INTO guests(id,first_name,last_name,reservation_id)VALUES('123-10-www-212010131-as','Farwa','Butt', '134ff985-e347-11eb-b486-9840bb2e8121' ) ";
            //SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String d1 = _formatter.format(new Date());
            //String d2=_formatter.format(new Date());

            //String query1="INSERT INTO reservation(id,check_in_date,check_out_date,guest_count,status)VALUES('141ff985-e347-11eb-b486-9840bb2e8121','"+d1+"','"+d2+"', 23, 'Hold' ) ";

            //int rt2=st1.executeUpdate(query1);
            int rt = stmt.executeUpdate( query );
            //if(rt2==1){
                //System.out.println("Insertion in Reservation Table is Successful");
            //}
            //else
                //System.out.println("Error Inserting in Table");

            if(rt==1){
                System.out.println("Insertion Successful");
            }
            else
                System.out.println("Could not Insert Data ");

            ResultSet rs=stmt.executeQuery("SELECT g.first_name,g.last_name,r.check_in_date from guests.reservation r Join guests.guests g on g.reservation_id=r.id;");
            while(rs.next()) {
                System.out.println(rs.getString("check_in_date") + "  " + rs.getString("first_name") + "  " + rs.getString("last_name"));
            }

        }catch(Exception ex){
            System.out.println(ex.toString());
        }finally {
            conn.close();
        }

    }

}
