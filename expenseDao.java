import java.util.Arrays;
import java.util.LinkedList;

// as MVC model class use fileopenner read and write file and do some operation for the data
public class expenseDao{
    private String filepath;
    
    private String[] col = new String[] {"item name" , "price" , "category" ,"date" };
    // private Class[] colClass = new Class[] {String.class,Double.class,String.class,String.class};
    private Object[][] row;
    

    //constructor read the data from test.csv
    public expenseDao(String filepath){
        setFilepath(filepath);
        read(read_csv());
    }

    // parse the linkedlist to Object[][] for the use of JTable
    public void read(LinkedList<String> str_list) {
        row = new Object[str_list.size()][4];
        for(int i =0 ; i<str_list.size(); i++){
            String str = str_list.get(i);
            LinkedList<String> list = new LinkedList<>(Arrays.asList(str.split(",")));
            for (int j=0; j<list.size(); j++){
                String obj = list.get(j);
                switch(j){
                case 0: row[i][j] = obj;
                        break;
                case 1: row[i][j] = Double.valueOf(obj);
                        break;
                case 2: row[i][j] = obj;
                        break;
                case 3: row[i][j] = obj;
                        break;
                default:  break;
                }
            }
        }
    }
    
    //getter for colname[index]
    public String getColumnName(int index){
        return col[index];
    }


    //getter for file path
    public String getFilepath() {
        return filepath;
    }

    //setter for file path
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    //getter for rows
    public Object[][] getRows() {
        return row;
    }

    // add a row to model
    public void  addRow(Object[] arr) {
        Object[][] new_arr = new Object[row.length+1][4];
        for (int i=0; i< row.length; i++){
            for(int j = 0 ; j < row[i].length; j++){
                new_arr[i][j] = row[i][j];
            }
        }
        new_arr[row.length] = arr;
        setRows(new_arr);
    }

    //set the rows in model
    public void setRows(Object[][] row) {
        this.row = row;
    }

    // update a row 
    public void updateRow(int index,Object[] arr){
        row[index] = arr;
    }

    // remove a row 
    public void removeRow(int index){
        Object[][] new_arr = new Object[row.length-1][4];
        for(int i=0, k=0;i<row.length;i++){
            if(i!=index){
                new_arr[k]=row[i];
                k++;
            }
        }
        setRows(new_arr);
    }

    //getter col[]
    public String[] getCol() {
        return col;
    }


    //setter col
    public void setCol(String[] col) {
        this.col = col;
    }


    // read csv file parse each line as a String and store in LinkedList 
    public LinkedList<String> read_csv(){
        fileOpener file = new fileOpener(filepath);
        file.readFile();
        LinkedList<String> str_list = file.getStr_list();
        return str_list;
    }

    // write the data to output.csv
    public void write_csv(){
        fileOpener file = new fileOpener("output.csv");
        file.writeFile(getRows(),"output.csv");
    }

    // summary the data and store them in LinkedList
    public LinkedList<Double> summarize(){
        //init list
        LinkedList<Double> list = new LinkedList<>();
        double sum = 0;

        //init value
        list.add(Double.valueOf(0));
        list.add(Double.valueOf(0));
        list.add(Double.valueOf(0));
        list.add(Double.valueOf(0));

        //get the data from model
        Object[][] data = getRows();

        //iterate the data and summary them
        for (int i=0; i<data.length; i++){
                double x =  (double) data[i][1];
                String cat = (String) data[i][2];
                if(cat.equals("Food")){
                    list.set(0,(double)list.get(0)+x);
                }else if(cat.equals("Electronics")){
                    list.set(1,(double)list.get(1)+x);
                }else if(cat.equals("Transportation")){
                    list.set(2,(double)list.get(2)+x);
                }else{
                    list.set(3,(double)list.get(3)+x);
                }
                sum += x;
        }

        // add all expense
        list.add(Double.valueOf(sum));
        return list;
    }

    //test the model here don't use this one as app
    public static void main(String[] args) { 
        expenseDao model = new expenseDao("test.csv");
        Object[][] row = model.getRows();
        for (int i=0; i<row.length; i++){
            for(int j=0; j<4; j++){
                System.out.println(row[i][j]);
            }
        }
        System.out.println("\nAfter update");
        

        model.updateRow(1,new Object[]{"Apple",Double.valueOf(10),"food","12-9-2022"});
        row = model.getRows();
        for (int i=0; i<row.length; i++){
            for(int j=0; j<4; j++){
                System.out.println(row[i][j]);
            }
        }
        } 
} 


    

    

