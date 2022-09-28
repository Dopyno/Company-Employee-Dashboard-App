package dao;


import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;


public class Store {
    private static Store INSTANCE;
    private String userName;

    private ListIterator listIterator = null;

    public ArrayList<Person> peopleList = new ArrayList<>();

    public File file = new File("PersonModelList.txt");

    private Store() throws Exception {
        this.loadPersonsFromFile();
    }

    public void addPerson(String userName, String password, String fullName, String emailAddress, String phoneNumber) throws IOException {
        this.peopleList.add(new Person(userName, password, fullName, emailAddress, phoneNumber));
        this.savePersonsToFile();
        System.out.println("Added successfully!");
    }
    public static Store getInstance() throws Exception {
      if(INSTANCE == null){
          INSTANCE = new Store();
      }

      return INSTANCE;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void loadPersonsFromFile(){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
            peopleList = (ArrayList<Person>)input.readObject();   // parse our Arraylist Contact
            input.close();
            this.printPersonsToConsole();
        } catch (IOException e) {
            System.out.println(e.getMessage());
//            new File(file.toURI());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void savePersonsToFile() throws IOException{
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oss = new ObjectOutputStream(fos);
            oss.writeObject(this.peopleList);
            oss.close();

            this.printPersonsToConsole();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(peopleList);
            System.out.println(e.getMessage());
        }
    }

    public void printPersonsToConsole(){
        System.out.println("---------------------------------------------------------");
        listIterator = peopleList.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        listIterator = null;
        System.out.println("---------------------------------------------------------");
    }
}
