package Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Customer extends AboveGod{
    String id;

    String name;

    String Country;

    String City;

    String Address;

    String Zip;

    String Phone;

    String Email;

    String AFM;

    float price;

    String domain;

    String invoices;


    //Domain,Expiry Date Key Pairs
   public Map<String, Date> Domains=new HashMap<>();

   public ArrayList<Domain> DomainsList = new ArrayList<>();

    ArrayList<Invoice> invoicesList=new ArrayList<>();

    public Customer(String id,String name,String Country,String City,String Address,String Zip,String Phone,String Email,String AFM)
    {
        this.id = id;
        this.name = name;
        this.Country = Country;
        this.City = City;
        this.Address = Address;
        this.Zip = Zip;
        this.Phone = Phone;
        this.Email = Email;
        this.AFM =AFM;
    }

    public Customer() {

    }

    public ArrayList<Invoice> GetInvoicesList()
    {
        return invoicesList;
    }


    public String[] CustomerOutputLine()
    {
        String DomainsOut ="";
        String DatesOut ="";
        String[] OutputArray = new String[5];

        String InvoicesCode = "";
        OutputArray[0] = name ;
        OutputArray[4] = "";

        int  [] InvoiceTypes = new int[invoiceTypes.size()];
            for(int i = 0;i<InvoiceTypes.length;i++)
            {
                InvoiceTypes[i] = 0;
            }

        //Get the invoices of the customer
        System.out.println("INV SIZE = " + InvoiceTypes.length);

        for (int i = 0;i<invoiceTypes.size();i++)
        {
            for(int j = 0;j<invoicesList.size();j++)
            {
                if(invoiceTypes.get(i).getType().equals(invoicesList.get(j).getType()))
                {
                    InvoiceTypes[i] += 1;
                }
            }

            InvoicesCode += InvoiceTypes[i];
        }

//        for(int i = 0;i<=invoicesList.size()-1;i++)
//        {
//            if(invoicesList.get(i).getType().equals("Support"))
//                InvoiceTypes[0] += 1;
//
//            if(invoicesList.get(i).getType().equals("Hosting_small"))
//                InvoiceTypes[1] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Hosting_medium"))
//                InvoiceTypes[2] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Hosting_big"))
//                InvoiceTypes[3] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Social_Media"))
//                InvoiceTypes[4] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Domain_gr"))
//                InvoiceTypes[5] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Domain_com"))
//                InvoiceTypes[6] = invoicesList.get(i).getTimes();
//
//            if(invoicesList.get(i).getType().equals("Flyer"))
//                InvoiceTypes[7] = invoicesList.get(i).getTimes();
//
//
//
//        }

        int j = 0;
        for (Map.Entry<String,Date> entry : Domains.entrySet())
        {
            if(j == Domains.size() - 1)
            {
                DomainsOut = DomainsOut + entry.getKey() ;
                DatesOut = DatesOut + entry.getValue() ;
            }
            else
            {
                DomainsOut = DomainsOut + entry.getKey() + ":";
                DatesOut = DatesOut + entry.getValue() + ":";
                j++;
            }

        }



        OutputArray[1] = DomainsOut ;
        OutputArray[2] = DatesOut ;
        OutputArray[3] = Float.toString(price);
        OutputArray[4] = OutputArray[4] + InvoicesCode;




        return OutputArray;
    }

    public void GetCustomerData()
    {
        System.out.println(getId() + " " + getCountry()+ " "+ getName() + " " + getAddress());
        for (int i = 0;i<DomainsList.size();i++)
        {
            System.out.println(DomainsList.get(i).DisplayDomain());
        }

        for (int i = 0;i<invoicesList.size();i++)
        {
            System.out.println(invoicesList.get(i).DisplayInvoiceData());
            System.out.println();
        }





    }




    public Customer(String name,HashMap domains)
    {
        setName(name);
        setDomains(domains);
        setId();

    }

    public Customer(String name,HashMap domains,float price,String invoices){
        setDomains(domains);
        setName(name);
        setInvoices(invoices);
        setInvoicesList();
        calculatePrice();
        //assigns the customer a random id each time
        setId();


    }

    public Customer(String name,String dom,float price,String invoices){
        setDomain(dom);
        setName(name);
        setPrice(price);
        setInvoices(invoices);

    }




    public void setDomains(Map<String, Date> domains) {
        Domains = domains;
    }

    public Map<String, Date> getDomains() {
        return Domains;
    }

    //
    public void setInvoices(String invoices) {
        this.invoices = invoices;
    }



    public void setInvoicesList() {


        String temp = this.invoices;
        int[] localList = new int[invoiceTypes.size()];
        for (int i = 0; i < temp.length(); i++) {
            localList[i] = temp.charAt(i) - '0';
//            System.out.println("In local List : "+localList[i]);
        }



        for (int k = 0; k <invoiceTypes.size(); k++) {
            if (localList[k] > 0)
                for(int j = 0;j<localList[k];j++)
                {

                        this.invoicesList.add(new Invoice(invoiceTypes.get(k).getType(), invoiceTypes.get(k).getPrice(), invoiceTypes.get(k).getRecurring()));
                    }

                }



        }


        //todo CheckMonth then charge

    //                    if (this.invoicesList.get(k).getType().equals("Hosting_big")||this.invoicesList.get(k).getType().equals("Hosting_small")||this.invoicesList.get(k).getType().equals("Hosting_medium")) {
//                        for (int o=0;o<Domains.size();o++){
//
//                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                            LocalDate deadline_date = LocalDate.parse(Domains.get(o).toString(), dtf);
//                            LocalDate currentDate = LocalDate.now();
//                            int paymentMonth=deadline_date.getMonthValue();
//                            int currentMonth=currentDate.getMonthValue();
//
//                            if (currentMonth==paymentMonth){
//                                this.invoicesList.add(new Invoice(invoiceTypes.get(k).getType(), invoiceTypes.get(k).getPrice(), invoiceTypes.get(k).getReccuring()));
//                            }
//                        }
//                    }else
//                    {



    

    
    public void calculatePrice(){
        price = 0;
       for (int i=0;i<invoicesList.size();i++){
//               System.out.println("" + this.name + "Type : " + invoicesList.get(i).getType() + " , Price : " + invoicesList.get(i).getPrice() + " , Times : ");


               this.price += invoicesList.get(i).getPrice();
           }
       }




   /* protected static int find(String query){
        customers.
        return customers.indexOf(query);
    }
*/


    public String getInvoices() {
        return invoices;
    }

    public Customer(Customer copy){
        setName(copy.getName());
        setDomain(copy.getDomain());
        setPrice(copy.getPrice());
        this.Domains = copy.Domains;
    }


    public boolean checkIfHis(String domain){
        return this.domain==domain;
    }





    public String getId() {
        return id;
    }

    public void setId() {
        this.id =String.valueOf(ThreadLocalRandom.current().nextInt(0, 10000000 + 1)) ;
    }

    public float getPrice() {
        return price;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {

    }

    public String getAddress() {
        return Address;
    }

    public String getAFM() {
        return AFM;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getZip() {
        return Zip;
    }

}
