import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    String firstName;
    String lastName;
    private customerAddress address;
    private String phoneNumber;
    private customerCreditCard creditCard;

    //    customer constructor and getters and setters
    public Customer(String firstName, String lastName, customerAddress address, String phoneNumber, customerCreditCard creditCard) {
        this.firstName = firstName;
        this.address = address;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public customerAddress getAddress() {
        return address;
    }

    public void setAddress(customerAddress address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public customerCreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(customerCreditCard creditCard) {
        this.creditCard = creditCard;
    }



    public static boolean removeCustomer(String phoneNumber) {
//        Use JsonController.customerList to make updates to the class list to be used in the json file

        JsonController controller = new JsonController();

        for (Customer person: controller.customerList) {
            if (phoneNumber.equals(person.getPhoneNumber())) {
                controller.customerList.remove(person);
                controller.serializeCustomers();
                return true;
            }

        }
        return false;



    }
}
