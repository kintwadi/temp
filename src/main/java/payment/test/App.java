package payment.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
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
	void addCard(Customer customer) throws StripeException {
		
		Map<String,Object>cardParams = new HashMap<String, Object>();
		cardParams.put("name", "John Doe");
		cardParams.put("number", "4012888888881881");
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
    public static void main( String[] args ) throws StripeException
    {
    	App app = new App();
    	app.getKey();
    	// create customer
    	//Customer customer = app.createCustomer("tester2@gmail.com", "created from code ");
    	
        // retrieve customer
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Customer customer = app.retrieveCurstomer("cus_EFYRLOTQdc2Zio");
    	//add card
    	app.addCard(customer);
    	System.out.println(gson.toJson(customer));
    }
}
