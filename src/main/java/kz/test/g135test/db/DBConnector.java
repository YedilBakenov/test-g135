package kz.test.g135test.db;

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<Student> getListStudents(){

        List<Student> students = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test.students");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setCity(resultSet.getString("city"));
                student.setFullName(resultSet.getString("full_name"));
                student.setGpa(resultSet.getDouble("gpa"));

                students.add(student);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return students;

    }

    public static void addStudent(Student st){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                    "test.students(full_name, gpa, city, age) VALUES (?, ?, ?, ?)");
            statement.setString(1, st.getFullName());
            statement.setDouble(2, st.getGpa());
            statement.setString(3, st.getCity());
            statement.setInt(4, st.getAge());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
