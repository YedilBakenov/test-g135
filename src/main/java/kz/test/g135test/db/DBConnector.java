package kz.test.g135test.db;

import kz.test.g135test.entity.Mark;
import kz.test.g135test.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private static Connection connection;
    private static String login = "postgres";
    private static String pass = "postgres";
    private static String url = "jdbc:postgresql://localhost:5432/g135?CurrentSchema=test";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getListStudents() {

        List<Student> students = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM test.students st " +
                    "INNER JOIN test.marks m " +
                    "ON st.mark_id = m.id ORDER BY st.id ASC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setCity(resultSet.getString("city"));
                student.setFullName(resultSet.getString("full_name"));

                Mark mark = new Mark();
                mark.setId(resultSet.getLong("mark_id"));
                mark.setGpa(resultSet.getDouble("gpa"));
                mark.setCharacterGpa(resultSet.getString("character_gpa"));
                student.setMark(mark);

                students.add(student);
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;

    }

    public static void addStudent(Student st) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                    "test.students(full_name, city, age, mark_id) VALUES (?, ?, ?, ?)");
            statement.setString(1, st.getFullName());
            statement.setString(2, st.getCity());
            statement.setInt(3, st.getAge());
            statement.setLong(4, st.getMark().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Mark> getAllMarks() {

        List<Mark> marks = new ArrayList<>();


        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test.marks");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mark mark = new Mark();
                mark.setId(resultSet.getLong("id"));
                mark.setGpa(resultSet.getDouble("gpa"));
                mark.setCharacterGpa(resultSet.getString("character_gpa"));

                marks.add(mark);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return marks;
    }

    public static Student getStudent(long id) {

        Student st = new Student();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM test.students st " +
                    "INNER JOIN test.marks m " +
                    "ON st.mark_id = m.id WHERE st.id=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                st.setId(resultSet.getInt("id"));
                st.setAge(resultSet.getInt("age"));
                st.setCity(resultSet.getString("city"));
                st.setFullName(resultSet.getString("full_name"));

                Mark mark = new Mark();
                mark.setId(resultSet.getInt("mark_id"));
                mark.setCharacterGpa(resultSet.getString("character_gpa"));
                mark.setGpa(resultSet.getDouble("gpa"));

                st.setMark(mark);

            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return st;
    }

    public static void updateSt(Student student) {

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE test.students SET " +
                    "full_name=?, mark_id=?, city=?, age=? WHERE id=?");
            statement.setString(1, student.getFullName());
            statement.setLong(2, student.getMark().getId());
            statement.setString(3, student.getCity());
            statement.setInt(4, student.getAge());
            statement.setInt(5, student.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteSt(long id) {

        try{

            PreparedStatement statement = connection.prepareStatement("DELETE FROM test.students WHERE id=?");
            statement.setLong(1, id);

            statement.executeUpdate();
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
