import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.*;

public class test3{

    public static void insertReservation(Connection conn,String res_id,int count) throws SQLException{

        conn.setAutoCommit(false);
        SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = _formatter.format(new Date());
        String d2=_formatter.format(new Date());

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
        st.setString(2,"2021-7-12");
        st.setString(3,"2021-7-20");
        st.setString(4,"HOLD");
        st.setInt(5,count);

        st.execute();
        conn.commit();

    }
    public static void insertGuest(Connection conn,String res_id,List<String> l) throws SQLException{

        PreparedStatement st=conn.prepareStatement("INSERT INTO guests(id,first_name,last_name,reservation_id)VALUES(?,?,?,?)");
        conn.setAutoCommit(false);
        for(String k : l) {

            String id= UUID.randomUUID().toString();
            String [] splitter=k.split(" ");
            st.setString(1, id);
            st.setString(2, splitter[0]);
            st.setString(3, splitter[1]);
            st.setString(4, res_id);
            st.execute();
            conn.commit();
        }



    }

    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        String res_id= UUID.randomUUID().toString();
        try
        {
            conn= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/guests","root","root");// Guests is the schema name

            List <String> l =new ArrayList<String>();
            l.add("Ahmed Sharif");
            l.add("Ayesha Ather");
            l.add("Abeera Ather");
            l.add("Shahid Afridi");


            test3.insertGuest(conn,res_id,l);
            System.out.println("Guests Record Inserted");
            test3.insertReservation(conn,res_id,l.size());
            System.out.println("Reservation Status Saved");


        }
        catch(Exception ex)
        {

        }
        finally
        {
            conn.rollback();
            conn.close();
        }
    }
}