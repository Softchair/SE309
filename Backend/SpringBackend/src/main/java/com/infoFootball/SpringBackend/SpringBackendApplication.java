package com.infoFootball.SpringBackend;

import com.infoFootball.SpringBackend.Chat.ConfigWebSocket;
import com.infoFootball.SpringBackend.Chat.FriendRelationController;
import com.infoFootball.SpringBackend.Chat.WebSocket;
import com.infoFootball.SpringBackend.GlobalChat.WebSocketServer;
import com.infoFootball.SpringBackend.Concessions.FoodItem.FoodItemController;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenuController;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenuService;
import com.infoFootball.SpringBackend.Concessions.Vendor.VendorController;
import com.infoFootball.SpringBackend.Login.LoginController;
import com.infoFootball.SpringBackend.Pins.PinController;
import com.infoFootball.SpringBackend.Register.RegisterController;
import com.infoFootball.SpringBackend.User.UserController;
import com.infoFootball.SpringBackend.Player.PlayerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration(proxyBeanMethods = false)
@EnableAutoConfiguration
@Import({LoginController.class, RegisterController.class, UserController.class, PinController.class, VendorController.class, LoginController.class, FoodMenuController.class,
		FoodMenuService.class, FoodItemController.class, PlayerController.class, WebSocket.class, WebSocketServer.class, ConfigWebSocket.class, FriendRelationController.class})
public class SpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

}