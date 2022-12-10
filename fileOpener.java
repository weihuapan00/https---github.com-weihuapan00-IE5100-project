import java.io.*;
import java.util.*;
public class fileOpener {
    private String filename;// filename location
    // init a list for storing each lines of text.
    private LinkedList<String> str_list = new LinkedList<>();
    
    
    //constructor that take the filename and set to local var
    public fileOpener(String filename){
       setFilename(filename);
    }

    // read file from filename
    public void readFile(){
        try {
            // using scanner to read file
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            // while the file has next line and put it in the list
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              str_list.add(data);
            }
            myReader.close();// close filereader
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    // write file to filename
    public void writeFile(Object[][] arr,String filename){
        //  set the filename that need to  write
        setFilename(filename);
        try {
            // init filewriter obj
            FileWriter myWriter = new FileWriter(filename);
            // for each line of text
            for (int i=0; i<arr.length ; i++){
              String toWrite = "";
              for (int j = 0; j<arr[i].length-1; j++){
                toWrite += arr[i][j].toString() +",";
              }
              toWrite += arr[i][3] + "\n";
              myWriter.write(toWrite);// write one line of str to file
            }
            // for (String str : arr.toString()){
            //     // split the text with comma, max 4 columns
            //     String[] list = str.split(",",4);
            //     String toWrite = ""; // empty str
            //     for(int i = 0; i<2; i++){ // get the first two str with comma
            //       toWrite += list[i] + ",";
            //     }
            //     toWrite += list[2] + "\n";// and last element and end of line char
            //     myWriter.write(toWrite);// write one line of str to file
            // }
            myWriter.close();// close the filewriter
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    //getter for filename
    public String getFilename() {
        return filename;
    }

    //setter for filename
    public void setFilename(String filename) {
        this.filename = filename;
    }

    //getter for list
    public LinkedList<String> getStr_list() {
        return str_list;
    }


}
