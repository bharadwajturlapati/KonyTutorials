package middlewareservices;

import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;

public class SessionPutService implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		Result result = new Result();
		
		request.getSession().setAttribute("sharedsessionkey", "sharedSessionValue");
		
		result.addParam(new Param("setvalueinsession", "true"));
		return result;
	}

}
