package middlewareservices;

import java.util.HashMap;
import java.util.Map;

import com.konylabs.middleware.api.OperationData;
import com.konylabs.middleware.api.ServiceRequest;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;
import com.konylabs.middleware.registry.AppRegistryException;

public class InvokeMockService implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		String valueInsessionExist = "false";
		
		// get the service request
		ServiceRequest serviceRequest = getServiceRequestwithSessionMapandDCR(request, "mockservice", "simplemockoperation", null, null,
				request.getHeaderMap());
		
		// some session play
		request.getSession().setAttribute("simpleSessionAttribute", "value of attribute");
		
		//invoking the request and getting the response
		String rep = serviceRequest.invokeServiceAndGetJson();
		
		// prepare result Object to return
		Result res = new Result();
		res.addParam(new Param("response", rep, "String"));
		
		//getting the param from session.
		String sessionParam = request.getSession().getAttribute("simpleSessionAttribute").toString();
		
		if(isStringBlankOrNull(sessionParam)) {
			valueInsessionExist = "true";
		}
		
		
		res.addParam(new Param("valueFromSession", valueInsessionExist));
		
		return res;
	}

	// with session map and with dcr
	public static ServiceRequest getServiceRequestwithSessionMapandDCR(DataControllerRequest request, String appID, String serviceID,
			String version, Map<String, Object> inputMap, Map<String, Object> headerMap) throws AppRegistryException {
		OperationData operationData = request.getServicesManager().getOperationDataBuilder().withServiceId(appID)
				.withOperationId(serviceID).withVersion(version).build();
		
		Map<String, Object> sessionMap = new HashMap<>();
		sessionMap.put("session_key1", "session_value1");

		ServiceRequest serviceRequest = request.getServicesManager().getRequestBuilder(operationData)
				.withDCRRequest(request).withInputs(inputMap == null ? new HashMap<String, Object>() : inputMap)
				.withHeaders(headerMap == null ? new HashMap<String, Object>() : headerMap)
				.withSession(sessionMap).build();
		return serviceRequest;
	}
	
	// with session map and without dcr
	public static ServiceRequest getServiceRequestwithSessionMapandWithoutDCR(DataControllerRequest request, String appID, String serviceID,
			String version, Map<String, Object> inputMap, Map<String, Object> headerMap) throws AppRegistryException {
		OperationData operationData = request.getServicesManager().getOperationDataBuilder().withServiceId(appID)
				.withOperationId(serviceID).withVersion(version).build();
		
		Map<String, Object> sessionMap = new HashMap<>();
		sessionMap.put("session_key1", "session_value1");

		ServiceRequest serviceRequest = request.getServicesManager().getRequestBuilder(operationData)
				.withInputs(inputMap == null ? new HashMap<String, Object>() : inputMap)
				.withHeaders(headerMap == null ? new HashMap<String, Object>() : headerMap)
				.withSession(sessionMap).build();
		return serviceRequest;
	}
	
	// without session map and with dcr
	public static ServiceRequest getServiceRequestwithoutSessionMapandWithDCR(DataControllerRequest request, String appID, String serviceID,
			String version, Map<String, Object> inputMap, Map<String, Object> headerMap) throws AppRegistryException {
		OperationData operationData = request.getServicesManager().getOperationDataBuilder().withServiceId(appID)
				.withOperationId(serviceID).withVersion(version).build();
		
		ServiceRequest serviceRequest = request.getServicesManager().getRequestBuilder(operationData)
				.withDCRRequest(request).withInputs(inputMap == null ? new HashMap<String, Object>() : inputMap)
				.withHeaders(headerMap == null ? new HashMap<String, Object>() : headerMap)
				.build();
		return serviceRequest;
	}
	
	// without session map and without dcr
	public static ServiceRequest getServiceRequestwithoutSessionMapandwithoutDCR(DataControllerRequest request, String appID, String serviceID,
			String version, Map<String, Object> inputMap, Map<String, Object> headerMap) throws AppRegistryException {
		OperationData operationData = request.getServicesManager().getOperationDataBuilder().withServiceId(appID)
				.withOperationId(serviceID).withVersion(version).build();
		
		ServiceRequest serviceRequest = request.getServicesManager().getRequestBuilder(operationData)
				.withInputs(inputMap == null ? new HashMap<String, Object>() : inputMap)
				.withHeaders(headerMap == null ? new HashMap<String, Object>() : headerMap)
				.build();
		return serviceRequest;
	}
	
	public static boolean isStringBlankOrNull(String input) {
		if(input == null) {
			return false;
		}
		if(input.length() == 0) {
			return false;
		}
		if("null".equalsIgnoreCase(input)) {
			return false;
		}
		return true;
	}

}
