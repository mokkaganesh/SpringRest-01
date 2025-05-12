package in.inueron.restcontroller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//RestController internally uses the @contoller and @Responsebody 
//so we can comment the contoller and response body
//use @RestController
//any way both will work same way


//@Controller
@RestController
@RequestMapping("/wish")
public class WishController {
	
	
	@GetMapping("/msg")
//	@ResponseBody
	public ResponseEntity<String> generateWishMessage(){
		//if we give String as return type --> it will act as a LogicalView name
		
		//means we want to act like response to some client
		
		LocalDateTime ldt=LocalDateTime.now();
		int hour = ldt.getHour();
		
		String body="";
		if(hour < 12) {
			body="good morning";
		}else if(hour < 16) {
			body="good afternoon";
		}else {
			body="good evening";
		}
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(body,HttpStatus.OK);
		return responseEntity;
		
		//we said controller and get mapping 
		//it will go to view resolver to execute.
		//use @ResponseBody -> the response will never sent to view resolver rather response will sent to the person who will hit the endpoint
		//here we dont want to send the view resolver
		
	}

}
