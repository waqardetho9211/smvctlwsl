package emp.web.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ibm.cics.server.InvalidRequestException;
import com.ibm.cics.server.Task;

@Controller
public class WebSecureController 
{
	/**
	 * @param model mvc model field
	 * @param auth auth received from server
	 * @return home home.html page
	 * @throws InvalidRequestException
	 */
	@GetMapping("/")
	public String home(Map<String, Object> model, Authentication auth) throws InvalidRequestException 
	{			
		// When this endpoint is invoked, the following values will be injected 
		// into home.html by the Thymeleaf templating engine
		model.put("message", "Hello World");
		model.put("title", "Hello Home");
		model.put("date", new Date());
		model.put("user", auth.getName());
		model.put("roles", ((UserDetails) auth.getPrincipal()).getAuthorities());
		
		// Additional fields to populate if we are running in CICS Liberty
		if (System.getProperty("com.ibm.cics.jvmserver.wlp.server.name") != null) 
		{
			model.put("cicsuser", Task.getTask().getUSERID());
			model.put("tranid", Task.getTask().getTransactionName());
		}
		
		return "home";
	}	
}
