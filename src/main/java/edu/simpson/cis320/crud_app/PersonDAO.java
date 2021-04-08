package edu.simpson.cis320.crud_app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.LinkedList;
import java.sql.PreparedStatement;

/**
 * Data Access Object for the Person table/class
 */
public class PersonDAO {
    private final static Logger log = Logger.getLogger(PersonDAO.class.getName());

    /**
     * Get a list of the people in the database.
     * @return Returns a list of instances of the People class.
     */
    public static List<Person> getPeople() {
        log.log(Level.INFO, "Get people");

        List<Person> list = new LinkedList<Person>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = DBHelper.getConnection();
            log.log(Level.INFO, "A");

            String sql = "select id, first, last, phone, birthday, email from person";

            stmt = conn.prepareStatement(sql);
            log.log(Level.INFO, "B");

            rs = stmt.executeQuery();

            while(rs.next()) {

                Person person = new Person();

                person.setId(rs.getInt("id"));
                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setPhone(rs.getString("phone"));
                person.setBirthday(rs.getString("birthday"));
                person.setEmail(rs.getString("email"));

                list.add(person);
            }
        } catch (SQLException se) {
            log.log(Level.SEVERE, "SQL Error", se );
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error", e );
        } finally {

            try { if (rs != null) rs.close(); }
            catch (Exception e) { log.log(Level.SEVERE, "Error", e ); }

            try { if(stmt != null) stmt.close(); }
            catch (Exception e) { log.log(Level.SEVERE, "Error", e ); }

            try { if(conn != null) conn.close(); }
            catch (Exception e) { log.log(Level.SEVERE, "Error", e ); }
        }
        // Done! Return the results
        return list;
    }

    public static void addPerson(Person person) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = DBHelper.getConnection();

            String sql = "INSERT INTO person (first, last, phone, birthday, email) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, person.getFirst());
            stmt.setString(2, person.getLast());
            stmt.setString(3, person.getPhone());
            stmt.setString(4, person.getBirthday());
            stmt.setString(5, person.getEmail());

            stmt.executeUpdate();

        } catch (SQLException se) {
            log.log(Level.SEVERE, "SQL Error", se );
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error", e );
        } finally {

            try { if(stmt != null) stmt.close(); }
            catch (Exception e) { log.log(Level.SEVERE, "Error", e ); }

            try { if(conn != null) conn.close(); }
            catch (Exception e) { log.log(Level.SEVERE, "Error", e ); }
        }
    }
}
