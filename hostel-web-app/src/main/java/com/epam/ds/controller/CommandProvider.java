package com.epam.ds.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.ds.controller.impl.ChangeLocal;
import com.epam.ds.controller.impl.LogOutCommand;
import com.epam.ds.controller.impl.LoginationCommand;
import com.epam.ds.controller.impl.RegistrationCommand;
import com.epam.ds.controller.impl.SaveAccauntInfoChangesCommand;
import com.epam.ds.controller.impl.SaveBookingRequest;
import com.epam.ds.controller.impl.gotocommand.GoToAccauntPage;
import com.epam.ds.controller.impl.gotocommand.GoToBookingPage;
import com.epam.ds.controller.impl.gotocommand.GoToBookingRequestsPage;
import com.epam.ds.controller.impl.gotocommand.GoToChangeAccauntInfo;
import com.epam.ds.controller.impl.gotocommand.GoToIndexPage;
import com.epam.ds.controller.impl.gotocommand.GoToLoginationPage;
import com.epam.ds.controller.impl.gotocommand.GoToManagementPage;
import com.epam.ds.controller.impl.gotocommand.GoToRegistrationPage;

public final class CommandProvider {
	private final Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("registration", new RegistrationCommand());
		commands.put("logination", new LoginationCommand());
		commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPage());
		commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPage());
		commands.put("GO_TO_INDEX_PAGE", new GoToIndexPage());
		commands.put("GO_TO_ACCAUNT_PAGE", new GoToAccauntPage());
		commands.put("SAVE_ACCAUNT_CHANGES", new SaveAccauntInfoChangesCommand());
		commands.put("GO_TO_CHANGE_ACCAUNT_INFO", new GoToChangeAccauntInfo());
		commands.put("LOGOUT", new LogOutCommand());
		commands.put("GO_TO_BOOKING_PAGE", new GoToBookingPage());
		commands.put("SAVE_BOOKING_REQUEST", new SaveBookingRequest());
		commands.put("CHANGE_LOCALE", new ChangeLocal());
		commands.put("GO_TO_MANAGEMENT_PAGE", new GoToManagementPage());
		commands.put("GO_TO_BOOKING_REQUESTS_PAGE", new GoToBookingRequestsPage());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
		
	}

}
