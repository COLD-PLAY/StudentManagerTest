import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by coldplay on 17-4-11.
 */
public class HandleStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVE = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/coldplay";

    static final String USER = "root";
    static final String PASS = "liaozhou1998";

    static String message = "";


    public HandleStudent() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        String handler = request.getParameter("handler");

        // insert
        if (handler.compareTo("insert") == 0) {
            message = "insert successfully!";
            try {
                Class.forName(JDBC_DRIVE);

                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                String name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
                String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"), "UTF-8");
                String id = request.getParameter("id");
                if (sex.compareTo("male") == 0) sex = "男";
                else sex = "女";

                String sql = "INSERT INTO stu value('" + name + "', '" + sex + "', '" + id + "');";

                stmt.execute(sql);

                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                message = "insert fail: " + se.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                message = "insert fail" + e.getMessage();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se) {

                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            request.setAttribute("message", message);

            request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }

        // delete
        if (handler.compareTo("delete") == 0) {
            message = "delete successfully!";
            try {
                Class.forName(JDBC_DRIVE);

                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                String name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
                String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"), "UTF-8");
                String id = request.getParameter("id");
                if (sex.compareTo("male") == 0) sex = "男";
                else sex = "女";

                String sql = "DELETE FROM stu WHERE name='" + name + "' AND sex='" + sex + "' AND id='" + id + "';";

                stmt.execute(sql);

                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                message = "delete fail: " + se.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                message = "delete fail" + e.getMessage();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se) {

                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            request.setAttribute("message", message);

            request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }

        // search
        if (handler.compareTo("search") == 0) {
            message = "search successfully!";
            String name = "";
            String sex = "";
            String id = "";
            try {
                Class.forName(JDBC_DRIVE);

                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
//                String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"), "UTF-8");
//                String id = request.getParameter("id");
//                if (sex.compareTo("male") == 0) sex = "男";
//                else sex = "女";

                String sql = "SELECT * FROM stu WHERE name='" + name + "';";

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    sex = rs.getString("sex");
                    id = rs.getString("id");
                }

                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                message = "search fail: " + se.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                message = "search fail" + e.getMessage();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se) {

                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            request.setAttribute("message", message);

            if (message.compareTo("search successfully!") == 0) {
                request.setAttribute("name", name);
                request.setAttribute("sex", sex);
                request.setAttribute("id", id);
                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
            }
            else request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }

        // update
        if (handler.compareTo("update") == 0) {
            message = "update successfully!";
            String name = "";
            String sex = "";
            String id = "";
            try {
                Class.forName(JDBC_DRIVE);

                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                name = new String(request.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
                sex = new String(request.getParameter("sex").getBytes("ISO8859-1"), "UTF-8");
                id = request.getParameter("id");
                if (sex.compareTo("male") == 0) sex = "男";
                else sex = "女";

                String sql = "UPDATE stu SET name='" + name + "', sex='" + sex + "', id='" + id + "';";

                stmt.execute(sql);

                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                message = "update fail: " + se.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                message = "update fail" + e.getMessage();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se) {

                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            request.setAttribute("message", message);
            if (message.compareTo("update successfully!") == 0) {
                request.setAttribute("name", name);
                request.setAttribute("sex", sex);
                request.setAttribute("id", id);
                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
            }
            else request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }
}
