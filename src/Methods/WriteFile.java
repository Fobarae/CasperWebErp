package Methods;


import Entities.AboveGod;
import Entities.Appointment;
import Entities.Customer;
import Entities.Domain;
import Entities.Project;
import calendar.ReadAppointments;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WriteFile extends AboveGod {



     public void SaveCustomerAdded(String name, String dom, String price) throws IOException {

          FileWriter pw = new FileWriter("src\\Csv\\customers.csv", true);
          pw.append(name);
          pw.append(",");
          pw.append(dom);
          pw.append(",");
          pw.append(price);
          pw.append("0000000");
          pw.append("\n");
          pw.flush();
          pw.close();

     }

     public void SaveNewDomains() throws IOException {
          FileWriter pw = new FileWriter("src\\Csv\\customers.csv", true);

     }


     public void SaveProjectsAdded(String name, String date, String price, String workforce) throws IOException {
          FileWriter pw = new FileWriter("src\\Csv\\Projects_test.csv", true);
          pw.append(name);
          pw.append(",");
          pw.append(date);
          pw.append(",");
          pw.append(workforce);
          pw.append(",");
          pw.append(price);
          pw.append("\n");
          pw.flush();
          pw.close();

     }

     public void SaveProjectsDeleted(String name, String date, String price, String workforce) throws IOException {
          FileWriter pw = new FileWriter("src\\Csv\\ArchivedProjects.csv", true);
          pw.append(name);
          pw.append(",");
          pw.append(date);
          pw.append(",");
          pw.append(workforce);
          pw.append(",");
          pw.append(price);
          pw.append("\n");
          pw.flush();
          pw.close();

     }

     public void SaveCustomerDeleted(String name, String dom, String price) throws IOException {

          FileWriter pw = new FileWriter("src\\Csv\\ArchivedCustomers.csv", true);
          pw.append(name);
          pw.append(",");
          pw.append(dom);
          pw.append(",");
          pw.append(price);
          pw.append("\n");
          pw.flush();
          pw.close();

     }



     public void UpdateHostingsFile() throws IOException {
          List<String[]> AllDomains = new ArrayList<>();

          for(Map.Entry<String, Domain> entry : DomainMap.entrySet())
          {
               AllDomains.add(entry.getValue().DomainOutputString());
          }

          CSVWriter writer = new CSVWriter(new FileWriter("src\\Csv\\Hostings.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
          writer.writeAll(AllDomains, false);
          writer.close();



     }


     //Updates the customer file when a customer is edited
     public void UpdateCustomerFile() throws IOException {



          System.out.println("Customer Edited ");


          List<String[]> allElements = new ArrayList<String[]>();

          for (Map.Entry<String, Customer> entry : customerMap.entrySet()
          ) {

               allElements.add(entry.getValue().CustomerOutputLine());

          }


          CSVWriter writer = new CSVWriter(new FileWriter("src\\Csv\\customers.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
          writer.writeAll(allElements, false);
          writer.close();

     }




     //todo make Deletes work with id not name
     public void DeleteCustomer(String id) throws IOException, CsvException {

          //Save into archives the completed Projects
          System.out.println("This is the id we have to delete : "+id);
          Customer archived=new Customer(customerMap.get(id));

          WriteFile wr=new WriteFile();

          wr.SaveCustomerDeleted(archived.getName(),archived.getDomain(),Float.toString(archived.getPrice()));

          System.out.println("Customer deleted : "+customerMap.remove(id));
          customerMap.remove(id);

          List<String[]> allElements = new ArrayList<String[]>();

          for (Map.Entry<String, Customer> entry : customerMap.entrySet()
          ) {

               allElements.add(entry.getValue().CustomerOutputLine());

          }


          CSVWriter writer = new CSVWriter(new FileWriter("src\\Csv\\customers.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
          writer.writeAll(allElements, false);
          writer.close();


     }











     public void DeleteProject(String id) throws IOException, CsvException {
          Project archived=new Project(projectMap.get(id));

          WriteFile wr=new WriteFile();

         wr.SaveProjectsDeleted(archived.getName(),archived.getDueDate().toString(),Float.toString(archived.getPrice()),Integer.toString(archived.getWorkforce()));
          System.out.println("Project id wanted for deletion : "+ id);


          projectMap.remove(id);

          List<String[]> allElements = new ArrayList<String[]>();

          for (Map.Entry<String, Project> entry : projectMap.entrySet()
          ) {
               System.out.println("Item added : "+entry.getValue().toStringArray());
               allElements.add(entry.getValue().toStringArray());

          }


          CSVWriter writer = new CSVWriter(new FileWriter("src\\Csv\\Projects_test.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
          writer.writeAll(allElements, false);
          writer.close();


     }


     public void SaveItemAdded (String type, String price, String recurring) throws IOException {

          FileWriter pw = new FileWriter("src\\Csv\\InvoiceItems.csv",true);
          pw.append(type);
          pw.append(",");
          pw.append(price);
          pw.append(",");
          pw.append(recurring);
          pw.append("\n");
          pw.flush();
          pw.close();

     }

     public void DeleteItem(String title) throws IOException, CsvException {

          ReadAppointments appointments = new ReadAppointments();
          ArrayList<Appointment> myAppointments = appointments.ReadFile("src\\Csv\\appointments.csv");

          myAppointments.removeIf(appointment -> appointment.getTitle().equals(title));

          FileWriter pw = new FileWriter("src\\Csv\\appointments.csv");

          for (Appointment appointment : myAppointments){
               pw.write(appointment.getTitle()+","+appointment.getDate()+","
                       +appointment.getTime()+","+appointment.getAttendee()+","
                       +appointment.getNotes()+","+appointment.getImportance()+"\n");
          }
          pw.flush();
          pw.close();

     }


}
