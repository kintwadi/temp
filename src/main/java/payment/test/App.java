package payment.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

/**
 * Hello world!
 *
 */
public class App 
{
	
	
	private String getKey() {
		return Stripe.apiKey = "sk_test_NPHLAicsrzGgjKOWE1blIB6h";
	}
	Customer createCustomer(String email,String description) throws StripeException {
		
		Map<String,Object> myCustomer = new HashMap<String, Object>();
		myCustomer.put("email", email);
		myCustomer.put("description", description);
		Customer sCustomer = Customer.create(myCustomer);
		return sCustomer;
	}
	void addCard(Customer customer,String cardNumber) throws StripeException {
		
		Map<String,Object>cardParams = new HashMap<String, Object>();
		cardParams.put("name", "John Doe");
		cardParams.put("number", cardNumber);
		cardParams.put("exp_month", "11");
		cardParams.put("exp_year", "2022");
		cardParams.put("cvc", "123");
		
		Map<String,Object>tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParams);
		Token token = Token.create(tokenParam);
		
		Map<String,Object>source= new HashMap<String, Object>();
		source.put("source", token.getId());
		
		customer.getSources().create(source);
		
	}
	Customer retrieveCurstomer(String id) throws StripeException {
	
		Customer customer = Customer.retrieve(id);
		return customer;
	}
	
	void pay(String customerId,String cardId) throws StripeException{
		
		Map<String,Object>chargeParams = new HashMap<String, Object>();
	
		
		chargeParams.put("amount","100000" );
		chargeParams.put("currency","usd" );
		chargeParams.put("customer",customerId);
		// pay with specific card
		chargeParams.put("source",cardId);
		
		Charge charge = Charge.create(chargeParams);
		
	}
    public static void main( String[] args ) throws StripeException
    {
    	App app = new App();
    	app.getKey();
    	// create customer
    	//Customer customer = app.createCustomer("tester2@gmail.com", "created from code ");
    	
        // retrieve customer
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Customer customer = app.retrieveCurstomer("cus_EFYr8xnMS2Zw7R");
    	//add card
    	//app.addCard(customer,"5555555555554444");
    	//app.addCard(customer,"378282246310005");
    	
    	app.pay(customer.getId(),"card_1DnN8CGiBBL3L59pUbVUK58R");
    	System.out.println(gson.toJson(customer));
    }
}
