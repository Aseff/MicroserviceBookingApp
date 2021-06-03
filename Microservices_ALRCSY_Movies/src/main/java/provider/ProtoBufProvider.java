package provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.JsonFormat;



@Provider
@Consumes({"application/x-protobuf", MediaType.APPLICATION_JSON })
@Produces({"application/x-protobuf", MediaType.APPLICATION_JSON })
public class ProtoBufProvider  implements MessageBodyReader<Message>,MessageBodyWriter<Message>{

 
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Message message, Class cls, Type type, Annotation[] annots, MediaType mediaType) {
		return -1;
	}
	
	@Override
	public void writeTo(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream stream)
			throws IOException, WebApplicationException {
		if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {
            String json = JsonFormat.printer().includingDefaultValueFields().print(message);
            OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
            streamWriter.write(json);
            streamWriter.flush();
		} else {
			message.writeTo(stream);
		}
		
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Message readFrom(Class<Message> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream stream)
			throws IOException, WebApplicationException {
		try {
			if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {
	            InputStreamReader streamReader = new InputStreamReader(stream);
                Method newBuilderMethod = type.getMethod("newBuilder");
                Builder builder = (Builder) newBuilderMethod.invoke(null);
	            JsonFormat.parser().ignoringUnknownFields().merge(streamReader, builder);
	            return builder.build();
			} else {
                Method parseFromMethod = type.getMethod("parseFrom", InputStream.class);
                return (Message) parseFromMethod.invoke(null, stream);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	

}
