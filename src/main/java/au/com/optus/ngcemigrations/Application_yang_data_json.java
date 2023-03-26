package au.com.optus.ngcemigrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import au.com.optus.ngcemigrations.RestHandler;
import javax.xml.bind.JAXBContext;
import java.io.StringReader;


public class Application_yang_data_json extends RestHandler {

	public Application_yang_data_json(ClassLoader classLoader,
			String handlingProcessId, String handlingStrategy) {
		super(classLoader, handlingProcessId, handlingStrategy);
	}

	public Application_yang_data_json(ClassLoader classLoader) {
		super(classLoader);
	}
	
	@Override
	protected String transformRequest(Object data, String contentType) {
		try {
			if (contentType.toLowerCase().contains("application/json")
					|| contentType.toLowerCase().contains(
							"application/yang-data+json")) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(data);
			}

		} catch (Exception var5) {
			throw new RuntimeException("Unable to transform request to object",
					var5);
		}

		throw new IllegalArgumentException(
				"Unable to find transformer for content type '" + contentType
						+ "' to handle data " + data);
	}

	@Override
    protected Object transformResult(Class<?> clazz,
                                     String contentType,
                                     String content) throws Exception {

        if (contentType.toLowerCase().contains("application/json")
        		|| contentType.toLowerCase().contains(
				"application/yang-data+json")) {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(content,
                                    clazz);
        } else if (contentType.toLowerCase().contains("application/xml")) {
            StringReader result = new StringReader(content);
            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{clazz});

            return jaxbContext.createUnmarshaller().unmarshal(result);
        }
            throw new IllegalArgumentException("Unable to find transformer for content type '" + contentType + "' to handle content" + content); 

        // unknown content type, returning string representation
        //return content;
    }
	public Application_yang_data_json() {
	}
}