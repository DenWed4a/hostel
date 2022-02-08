package com.epam.ds.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.ds.controller.impl.ChangeLocal;
import com.epam.ds.controller.impl.ChangeUserRole;
import com.epam.ds.controller.impl.DeleteBookingRequest;
import com.epam.ds.controller.impl.LogOutCommand;
import com.epam.ds.controller.impl.LoginationCommand;
import com.epam.ds.controller.impl.RegistrationCommand;
import com.epam.ds.controller.impl.SaveAccauntInfoChangesCommand;
import com.epam.ds.controller.impl.SaveBookingChangesCommand;
import com.epam.ds.controller.impl.SaveBookingRequest;
import com.epam.ds.controller.impl.SaveConfirmedRequest;
import com.epam.ds.controller.impl.SaveUserImageCommand;
import com.epam.ds.controller.impl.UpdateBillPayment;
import com.epam.ds.controller.impl.gotocommand.GoToAccauntPage;
import com.epam.ds.controller.impl.gotocommand.GoToBillPage;
import com.epam.ds.controller.impl.gotocommand.GoToBookingPage;
import com.epam.ds.controller.impl.gotocommand.GoToBookingProcessingPage;
import com.epam.ds.controller.impl.gotocommand.GoToBookingRequestsPage;
import com.epam.ds.controller.impl.gotocommand.GoToChangeAccauntInfo;
import com.epam.ds.controller.impl.gotocommand.GoToChangeBookingRequest;
import com.epam.ds.controller.impl.gotocommand.GoToErrorPage;
import com.epam.ds.controller.impl.gotocommand.GoToGalleryPage;
import com.epam.ds.controller.impl.gotocommand.GoToIndexPage;
import com.epam.ds.controller.impl.gotocommand.GoToLockersListPage;
import com.epam.ds.controller.impl.gotocommand.GoToLoginationPage;
import com.epam.ds.controller.impl.gotocommand.GoToManagementPage;
import com.epam.ds.controller.impl.gotocommand.GoToPlacesListPage;
import com.epam.ds.controller.impl.gotocommand.GoToProformaInvoicePage;
import com.epam.ds.controller.impl.gotocommand.GoToRegistrationPage;
import com.epam.ds.controller.impl.gotocommand.GoToReviewsPage;
import com.epam.ds.controller.impl.gotocommand.GoToUserInfoPage;
import com.epam.ds.controller.impl.gotocommand.GoToUserList;
import com.epam.ds.controller.impl.gotocommand.GoTo_Test;

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
		commands.put("GO_TO_ERROR_PAGE", new GoToErrorPage());
		commands.put("GO_TO_PROFORMA_INVOICE_PAGE", new GoToProformaInvoicePage());
		commands.put("GO_TO_USER_INFO_PAGE", new GoToUserInfoPage());
		commands.put("GO_TO_BOOKING_PROCESSING_PAGE", new GoToBookingProcessingPage());
		commands.put("GO_TO_TEST", new GoTo_Test());
		commands.put("SAVE_CONFIRMED_REQUEST", new SaveConfirmedRequest());
		commands.put("DELETE_BOOKING_REQUEST", new DeleteBookingRequest());
		commands.put("GO_TO_USER_LIST", new GoToUserList());
		commands.put("GO_TO_CHANGE_BOOKING_REQUEST", new GoToChangeBookingRequest());
		commands.put("SAVE_BOOKING_CHANGES", new SaveBookingChangesCommand());
		commands.put("GO_TO_BILL_PAGE", new GoToBillPage());
		commands.put("UPDATE_PAYMENT", new UpdateBillPayment());
		commands.put("SAVE_USER_IMAGE", new SaveUserImageCommand());
		commands.put("CHANGE_USER_ROLE", new ChangeUserRole());
		commands.put("GO_TO_PLACES_LIST_PAGE", new GoToPlacesListPage());
		commands.put("GO_TO_LOCKERS_LIST_PAGE", new GoToLockersListPage());
		commands.put("GO_TO_GALLERY_PAGE", new GoToGalleryPage());
		commands.put("GO_TO_REVIEWS_PAGE", new GoToReviewsPage());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
		
	}

}
