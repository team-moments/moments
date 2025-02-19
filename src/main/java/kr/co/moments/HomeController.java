package kr.co.moments;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home"; // home.jsp 또는 home.html이 있어야 함
    }
}

//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//
//	@GetMapping("/")
//	public String home() {
//		return "home";
//	}
////	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
////
////	@RequestMapping(value = "/", method = RequestMethod.GET)
////	public String home(Locale locale, Model model) {
////		logger.info("Welcome home! The client locale is {}.", locale);
////		
////		Date date = new Date();
////		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
////		
////		String formattedDate = dateFormat.format(date);
////		
////		model.addAttribute("serverTime", formattedDate );
////		
////		return "home";
////	}
//	
//}
