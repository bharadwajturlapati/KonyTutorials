package middlewareservices;

import com.konylabs.middleware.common.JavaService2;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.controller.DataControllerResponse;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Result;

public class SessionGetService implements JavaService2 {

	public Object invoke(String methodName, Object[] inputParams, DataControllerRequest request,
			DataControllerResponse response) throws Exception {
		Result result = new Result();
		String value = "";
		
		if(request.getSession().getAttribute("sharedsessionkey") != null) {
			value = request.getSession().getAttribute("sharedsessionkey").toString();
			result.addParam(new Param("sharedsessionkey", value));
		}
		if(request.getSession().getAttribute("session_key1") != null) {
			value = request.getSession().getAttribute("session_key1").toString();
			result.addParam(new Param("session_key1", value));
		}
		
		if(value == ""){
			result.addParam(new Param("errmessage", "value does not exist in session"));
		}
		return result;
	}

}
