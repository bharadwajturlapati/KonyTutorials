package middlewareservices;

import com.konylabs.middleware.api.ServiceRequest;
import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Result;

public class ServiceManagerWithoutSessionMapWithDCR  implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		ServiceRequest serviceRequest = InvokeMockService
				.getServiceRequestwithoutSessionMapandWithDCR(request, "sessionjavaservice", "sessiongetservice", null, null,
				request.getHeaderMap());
		
		Result rep = serviceRequest.invokeServiceAndGetResult();
		
		return rep;
	}

}

