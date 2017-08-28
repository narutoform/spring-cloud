package cn.chinotan.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Slf4j
public class XssStringJsonSerializer extends JsonSerializer<String> {

    // 不能少
    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (s != null){
            String htmlEscape = HtmlUtils.htmlEscape(s);
            jsonGenerator.writeString(htmlEscape);
            log.info("jsontmlEscape转化");
        }
    }
}
