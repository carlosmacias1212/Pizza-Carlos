import java.util.ArrayList;
import java.util.List;

/*
I recommend making Drink and Pizza inherit from something general, like FoodItem or MenuItem.
Then Order can have a list of FoodItems rather than requiring a
 Pizza, Size, Drink, etc. in the Order constructor (what if the customer doesn't order a Drink, for example?).
 */
public class Order {



    List<FoodItems> items = new ArrayList<FoodItems>();
    float orderTotal;
    String payment = "";
    String customerPhone;
    Boolean pickup;
    int orderID;

    public Order(String phone, Boolean pickup) {
        customerPhone = phone;
        this.pickup = pickup;

        JsonController controller = new JsonController();
        orderID = controller.orderList.size();

    }


    public String getCustomerPhoneNumber() {
        return customerPhone;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhone = customerPhoneNumber;
    }


    public float getPrice() {
        return orderTotal;
    }

    public void setPrice() {
        this.orderTotal = calculatePrice();
    }

    public Boolean getOrderType() {
        return pickup;
    }

    public void setOrderType(Boolean orderType) {
        this.pickup = orderType;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }



    public boolean addToCart(List<FoodItems> add){
        for (FoodItems cart: add) {
            items.add(cart);
        }
        return true;
    }

    public boolean confirmOrder(){


        return true;
    }

    public void selectPaymentMethod(String userSelection){
         payment = userSelection;
    }

    public void generateReceipt(){
       for(FoodItems item: items) {
           System.out.print(item.getFoodName() + " ");
           System.out.println(item.getPrice());

       }

       if(payment.equals("credit")) {
           System.out.println("Sign Here");
       }
    }


    public void setOrderTotal() {
        orderTotal = calculatePrice();
    }
    public float calculatePrice(){
//        calculate total price for the order

        float p = 0;
        for (FoodItems f: items) {
            p += f.getPrice();
        }


        return p;

    }
}



