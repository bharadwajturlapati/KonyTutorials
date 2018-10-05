package middlewareservices;

import com.konylabs.middleware.api.ServiceRequest;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;

public class InvokeServiceAndGetResult implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		
		ServiceRequest serviceRequest = InvokeMockService
				.getServiceRequestwithSessionMapandDCR(request, "sessionjavaservice", "sessiongetservice", null, null,
				request.getHeaderMap());
		
		Result rep = serviceRequest.invokeServiceAndGetResult();
		
		rep.addParam(new Param("response",rep.getParamValueByName("sharedSessionkey")));
		
		return rep;
	}

}