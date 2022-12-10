public class App {

    public static void main(String[] args) {
        GUI view = new GUI();
        expenseDao model = new expenseDao("test.csv");
        Controller c  = new Controller(model, view);
        c.initController();
    }
}
