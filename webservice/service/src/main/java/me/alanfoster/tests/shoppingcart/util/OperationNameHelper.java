package me.alanfoster.tests.shoppingcart.util;

import java.beans.Introspector;
import org.apache.camel.Handler;
import org.apache.camel.Header;

public class OperationNameHelper {
	@Handler
	public String getOperationName(@Header("operationName") String pascalCaseOperationName) {
		String camelCaseOperationName = Introspector.decapitalize(pascalCaseOperationName);
		return camelCaseOperationName;
	}
}