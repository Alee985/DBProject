import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class test2{

    public static void insert_res(Connection conn,String res_id) throws SQLException{

        SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = _formatter.format(new Date());
        String d2=_formatter.format(new Date());
        int count=22;
        PreparedStatement st=conn.prepareStatement("" +
                "INSERT INTO reservation (\n" +
                "   id,\n" +
                "   check_in_date,\n" +
                "   check_out_date,\n" +
                "   status,\n" +
                "   guest_count\n" +
                ") VALUES (\n" +
                "  ?,\n" +
                "  ?,\n" +
                "  ?,\n" +
                "  ?\n," +
                "  ?\n" +
                ")");
        st.setString(1,res_id);
        st.setString(2,d1);
        st.setString(3,d2);
        st.setString(4,"HOLD");
        st.setInt(5,count);

        int i= st.executeUpdate();
        System.out.println(i + " record inserted in Reservation table");

    }
    public static void insertguest(Connection conn,String res_id) throws SQLException{
        String id= "012012-21212-21212-wqw-12";
        PreparedStatement st=conn.prepareStatement("INSERT INTO guests(id,first_name,last_name,reservation_id)VALUES(?,?,?,?)");

        st.setString(1,id);
        st.setString(2,"Farwa");
        st.setString(3,"Afzaal");
        st.setString(4,res_id);

        int i=st.executeUpdate();

        System.out.println( i + " Record Inserted in Guests Table");
    }

    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        String res_id= UUID.randomUUID().toString();
        try
        {
            conn= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/guests","root","root");// Guests is the schema name


            test2.insertguest(conn,res_id);
            test2.insert_res(conn,res_id);



        }
        catch(Exception ex)
        {

        }
        finally
        {
            conn.close();
        }
    }
}