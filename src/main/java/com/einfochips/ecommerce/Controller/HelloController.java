package com.einfochips.ecommerce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//	@Autowired
//    private WebClient webClient;
	
	@GetMapping("/")
	public String home() {
		return "this is home";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello,Welcome to Ecommerce Website";
	}

//    @GetMapping("/api/hello")
//    public String hello(Principal principal) {
//        return "Hello " +principal.getName()+", Welcome to Daily Code Buffer!!";
//    }

//    @GetMapping("/api/users")
//    public String[] users(
//            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
//                    OAuth2AuthorizedClient client){
//        return this.webClient
//                .get()
//                .uri("http://127.0.0.1:8090/api/users")
//                .attributes(oauth2AuthorizedClient(client))
//                .retrieve()
//                .bodyToMono(String[].class)
//                .block();
//    }

}
