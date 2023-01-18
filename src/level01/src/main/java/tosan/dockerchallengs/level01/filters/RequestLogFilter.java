package tosan.dockerchallengs.level01.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author Sajad Salimzadeh
 * @since 1/18/2023
 */
@Component
@Slf4j
public class RequestLogFilter extends AbstractRequestLoggingFilter {

    private static final String JSON_CONTENT_TYPE = "application/json";

    public RequestLogFilter() {
        setIncludePayload(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected String getMessagePayload(HttpServletRequest request) {
        var contentType = request.getContentType();
        if (contentType != null && !contentType.equalsIgnoreCase(JSON_CONTENT_TYPE)) return null;

        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = buf.length;
                try {
                    var content = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    var mapper = new ObjectMapper();
                    return mapper.writeValueAsString(content);
                } catch (UnsupportedEncodingException e) {
                    return "[unknown]";
                } catch (Exception e) {
                    log.warn("Exception occurred in reading request payload. Exception message is: " + e.getMessage());
                }
            } else {
                if (request.getContentLength() > 0) {
                    return "Payload is empty. But request might have payload that hasn't read yet.";
                }
            }
        }
        return null;
    }
}
