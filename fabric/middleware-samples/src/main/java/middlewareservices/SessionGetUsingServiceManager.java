package middlewareservices;

import com.konylabs.middleware.api.ServiceRequest;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;

public class SessionGetUsingServiceManager implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		Result result = new Result();
		
		ServiceRequest serviceRequest = InvokeMockService
				.getServiceRequestwithSessionMapandDCR(request, "sessionjavaservice", "sessiongetservice", null, null,
				request.getHeaderMap());
		
		String rep = serviceRequest.invokeServiceAndGetJson();
		
		result.addParam(new Param("response",rep));
		
		return result;
	}

}
