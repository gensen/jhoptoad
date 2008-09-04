
package com.sniflabs.hoptoad;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlConfig;
import net.sourceforge.yamlbeans.YamlWriter;

/**
 * <strong>HoptoadNotice:</strong><br/>
 * A Java wrapper for hoptoad error reporting application, because 
 * <em>java guys need access to cool error reporting too!!</em><br/>
 * <br/>
 * <strong>requires:</strong> <br/>
 * <a href="http://sourceforge.net/projects/yamlbeans/">yamlbeans</a> from
 * http://sourceforge.net/projects/yamlbeans/ <br/>
 * <br/>
 *
 * 
 * <strong>Sample use:</strong><br/>
 * <code>
 * catch (Exception exp) {
 *   HoptoadNotice notice = 
 *   	new HoptoadNotice("fff9de2222222e156015fb875844de43",exp);
 *   notice.postData(true);
 * } 
 * </code>
 * 
 * 
 * 
 * @see <a href="http://sourceforge.net/projects/yamlbeans/">yamlbeans</a> 
 * @author Noah Paessel (noah@sniflabs.com)
 * @version 0.1 (may be quite flakey!)
 */
public class HoptoadNotice {
	
	public static final String	HOPTOAD_URL="http://hoptoadapp.com/notices/";

	public String 				api_key;
	public String 				error_message;
	public String				error_class;
	
	public ArrayList<String>		backtrace;
	public HashMap<String, String>	session;
	public HashMap<String, String>	request;
	public Map<String, String>		environment;	
	
	
	/**
	 * default constructor. Doesn't help anyone, but is required for bean-ness.
	 * initializes data structures.
	 */
	public HoptoadNotice() {
		super();
		this.api_key = "invalid key";
		backtrace = new ArrayList<String>();
		
		session = new HashMap<String, String>();
		request = new HashMap<String, String>();
		environment = new HashMap<String, String>();
	}
	

	/**
	 * Create a new HoptoadNotice notice for sending to hoptoadapp.com <br>
	 * <strong>NOTE:</strong> this does not send actually send the notice
	 * @param key Your HoapToadApp API key.
	 * @param exception the exception you want to log
	 * 
	 */
	public HoptoadNotice(String key, Throwable exception) {
		this();
		api_key = key;
		error_message = exception.getMessage();
		if (null == error_message || error_message.equals("")) {
			error_message = exception.toString();
		}
		error_class   = exception.getClass().getSimpleName();
		environment   = System.getenv();
		
		for(StackTraceElement element : exception.getStackTrace()) {
			backtrace.add(String.format("%s:%d  %s %s", 
					element.getFileName(), 
					element.getLineNumber(), 
					element.getClassName(), 
					element.getMethodName()));
		}
	}
	

	/**
	 * return the YAML string for this notice.<br/>
	 * <strong>NOTE:</strong> HoptoadNotice reuqires a java YAML library called
	 * yamlbeans.  you can get it http://sourceforge.net/projects/yamlbeans/
	 * <strong>NOTE:</strong> It seems like hoptoad hates the "!" thingies that
	 * this library creates. 
	 * 
	 * I don't feel like understanding what this is all about,
	 * so I replaced the "!" thingies and class names with NOTHING. Huh.
	 * @see https://sourceforge.net/projects/yamlbeans/
	 * @return the YAML for the HopToadNotice datastructure
	 */
	public String toYaml() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HashMap<String,Object> values = new HashMap<String, Object>();

		YamlConfig config = new YamlConfig();
		config.writeConfig.setUseVerbatimTags(false);
		config.writeConfig.setExplicitFirstDocument(true);
		config.writeConfig.setWriteDefaultValues(true);
		config.writeConfig.setWrapColumn(2000);
		config.writeConfig.setIndentSize(2);
		YamlWriter writer = new YamlWriter(new OutputStreamWriter(out),config);
		
		values.put("notice",this);
		try {
			writer.write(values);
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String returnString=out.toString();
		returnString = returnString.replaceAll("!\\S+", "");
		return returnString;
	}
	
	/**
	 * Send the data to the HopToadApp.
	 * Sets HTTP headers, and then sends the YAML file.
	 * Optionally prints out the YAML text if you pass in true for debug.
	 * @param debug set to true to see the YAML sent, and the XML server results
	 */
	public void postData(final boolean debug) {
		try {
			// establish connection to hoptoad
			URLConnection conn = new URL(HOPTOAD_URL).openConnection();

			// set up the connection to handle posting x-yaml document.
			// and for receiving XML formatted response..
			// HEY HOPTOAD KIDS: Can I send XML instead of YAML?
			// us java guys are just broken that way!
			conn.setRequestProperty(
					"Content-type",
					"application/x-yaml");
			conn.setRequestProperty(
					"Accept",
					"text/xml, application/xml");
			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(conn
					.getOutputStream());
			if (debug) {
				System.out.println(toYaml());
			}
			wr.write(toYaml());
			wr.flush();
			wr.close();

			// print out debugging messages
			if (debug) {
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					System.out.println(line);
				}
				rd.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.printf("error posting to HopToadApp: %s\n", e);
		}
	}
	
	
}



