
public class app {
    public static void main(String[] args) {
        try {
            ExtractData.fillSubscriptionsIntoCourses().forEach(e-> System.out.print(e.name +" "+ e.avgSubscriptionsPerMonth()+"\n"));
        } catch (RuntimeException ex){
            System.err.println(ex.getMessage());
        }

    }
}
