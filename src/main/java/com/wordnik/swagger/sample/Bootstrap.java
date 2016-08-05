package com.wordnik.swagger.sample;

import javax.servlet.http.HttpServlet;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.model.ApiInfo;

public class Bootstrap extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5316031531773899519L;

	static {
		ApiInfo info = new ApiInfo("Swagger Sample App", /* title */
				"This is a sample server User server.  You can find out more about Swagger "
						+ "at <a href=\"http://swagger.wordnik.com\">http://swagger.wordnik.com</a> or on irc.freenode.net, #swagger.  For this sample, "
						+ "you can use the api key \"special-key\" to test the authorization filters",
				"http://helloreverb.com/terms/", /* TOS URL */
				"apiteam@wordnik.com", /* Contact */
				"Apache 2.0", /* license */
				"http://www.apache.org/licenses/LICENSE-2.0.html" /*
																	 * license URL
																	 */
		);

		ConfigFactory.config().setApiInfo(info);
	}

}
