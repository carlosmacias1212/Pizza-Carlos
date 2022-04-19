import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class Main {



    public static void createCustomer() {
        JsonController controller = new JsonController();
        Scanner input = new Scanner(System.in);


        String[] mandatoryInputs = {"FirstName: ", "LastName: ", "Phone Number:",
                "Street Address: ", "City: ", "State: ", "Zip Code: "};

        String[] optionalInputs = {"Name on Card: ", "Card Number: ", "CSV: ", "Exp: "};

        Customer newCustomer;
        customerAddress newCustomerAddress;
        customerCreditCard newCustomerCredit;

        String[] mandParam = new String[7];
        String[] optParam = new String[4];

        for (int i = 0; i < mandatoryInputs.length; i++) {
            System.out.println(mandatoryInputs[i]);
            mandParam[i] = input.nextLine();
        }

        for (int i = 0; i < optionalInputs.length; i++) {
            System.out.println(optionalInputs[i]);
            optParam[i] = input.nextLine();
        }

        newCustomerAddress = new customerAddress(mandParam[3], mandParam[4], mandParam[5], mandParam[6]);
        newCustomerCredit = new customerCreditCard(optParam[0], optParam[1], optParam[2], optParam[3]);

        newCustomer = new Customer(mandParam[0], mandParam[1], newCustomerAddress, mandParam[2], newCustomerCredit);

        controller.customerList.add(newCustomer);
        controller.serializeCustomers();
    }

    public static void createOrder() {
        Scanner input = new Scanner(System.in);
        String phone;
        boolean pickup;
        char pick;

        System.out.println("Customer Phone: ");
        phone = input.nextLine();

        System.out.println("Pickup or Delivery (P/D): ");
        pick = input.next().charAt(0);
        input.nextLine();

        if (pick == 'P') {
            pickup = true;
        } else {
            pickup = false;
        }

        Order order = new Order(phone, pickup);

        ArrayList<FoodItems> foods = new ArrayList<FoodItems>();



        int addCart = 1;

        while(addCart == 1) {

            JsonController controller = new JsonController();
            char buildPage;

            System.out.println("BYO (Y/N): ");
            buildPage = input.next().charAt(0);
            input.nextLine();

            if (buildPage == 'Y') {
                String[] questions = {"Sauce: ", "Cheese: ", "Size: ", "Crust: "};
                String[] attributes = new String[4];
                ArrayList<String> toppings = new ArrayList<String>();

                for (int i = 0; i < attributes.length; i++) {
                    System.out.println(questions[i]);
                    attributes[i] = input.nextLine();
                }

                for (int i = 0; i < 4; i++) {
                    System.out.println("Topping " + i + ": ");
                    String topping = input.nextLine();
                    if (topping.length() > 2) {
                        toppings.add(topping);
                    }
                }

                FoodItems byo = new BYO(attributes[0], attributes[1], attributes[2], attributes[3], toppings);
                byo.setFoodName("BYO");
                foods.add(byo);

            } else {

                String[] questions = {"Pizza: ", "Side: ", "Drink: "};

                System.out.println("Pizza (Y/N): ");
                char pizza = input.next().charAt(0);
                input.nextLine();

                if(pizza == 'Y') {
                    System.out.println("Type: ");
                    String type = input.nextLine();

                    System.out.println("Size: ");
                    String size = input.nextLine();

                    Pizza special = new Pizza(type, size);
                    foods.add(special);

                }

                System.out.println("Side (Y/N): ");
                char side = input.next().charAt(0);
                input.nextLine();

                if (side == 'Y') {
                    System.out.println("What Side: ");
                    String selection = input.nextLine();

                    Side s = new Side(selection);
                    foods.add(s);

                }

                System.out.println("Drink(Y/N): ");
                char drink = input.next().charAt(0);
                input.nextLine();

                if (drink == 'Y') {
                    System.out.println("What Drink: ");
                    String drank = input.nextLine();

                    System.out.println("Size: ");
                    String sz = input.nextLine();

                    Drink d = new Drink(drank, sz);
                    foods.add(d);
                }



            }

            System.out.println("Add to Cart (1/0): ");
            addCart = input.nextInt();
            input.nextLine();

        }

        order.addToCart(foods);

        System.out.println("Payment Method (cash, credit, check): ");
        String payment = input.nextLine();

        order.selectPaymentMethod(payment);
        order.setOrderTotal();

        JsonController controller = new JsonController();
        controller.orderList.add(order);
        controller.serializeOrders();


        System.out.println("ORDER SUCCESSFUL:\n" +
                "YOUR RECIEPT- ");
        order.generateReceipt();






    }

    public static void main(String[] args) throws IOException {

        JsonController controller = new JsonController();
        controller.deserializeCustomers();
        controller.deserializeOrders();

        createOrder();




    }



}
