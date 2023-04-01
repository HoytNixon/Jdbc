import java.util.Map;
import java.util.TreeMap;

public class Course {
    public long id;
    public String name;

    public Map<Long,Integer> subscriptionsPerMonth ;

    public Course() {
        subscriptionsPerMonth = new TreeMap<>();
    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
        subscriptionsPerMonth = new TreeMap<Long,Integer>();
    }
    public double avgSubscriptionsPerMonth(){
        long countOfMonthWithSubs = subscriptionsPerMonth.values().stream().filter(e->e!=0).count();
        long sumOfSubs = subscriptionsPerMonth.values().stream().reduce(0, Integer::sum);
        if (countOfMonthWithSubs == 0){
            throw new RuntimeException("У курса " +name+ " не было покупок в этом году");
        }
        return  ((double)sumOfSubs / (double)countOfMonthWithSubs);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
