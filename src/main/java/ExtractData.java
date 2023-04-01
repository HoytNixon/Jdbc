import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtractData {
    public static String getCoursesQuery = "SELECT id,name FROM skillbox.courses;";
    private static String getSubscriptionQuery = "SELECT * FROM skillbox.subscriptions " +
            "where month(subscription_date) = ? and course_id = ?;";
    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection =DButils.getConnection();
             PreparedStatement ps = connection.prepareStatement(getCoursesQuery);){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Course course = new Course(id, name);
                courses.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }
    public static List<Course> fillSubscriptionsIntoCourses(){
        List<Course> courses = getCourses();
        try (Connection connection =DButils.getConnection();
             PreparedStatement ps = connection.prepareStatement(getSubscriptionQuery);) {
            for (Course course : courses) {
                for (long i = 1; i<13; i++){
                    ps.setLong(1,i);
                    ps.setLong(2, course.getId());
                    ResultSet rs = ps.executeQuery();
                    int howManyPurchases = 0;
                    while (rs.next()){
                        howManyPurchases+=1;
                    }
                    course.subscriptionsPerMonth.put(i,howManyPurchases);
                }
            }
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        return courses;
    }
}

