package com.gudratli.commonlibrary.httpclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpField;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggerHttpClient extends HttpClient
{
    @Override
    public Request newRequest(URI uri)
    {
        Request request = super.newRequest(uri);
        return enhance(request);
    }

    private Request enhance(Request request)
    {
        StringBuilder requestLog = new StringBuilder("\n");
        requestLog.append("<----------------------------- NEW REQUEST --------------------------------->\n");
        logRequest(request, requestLog);
        logResponse(request, requestLog);

        return request;
    }

    private void logRequest(Request request, StringBuilder requestLog)
    {
        request.onRequestBegin(theRequest ->
        {
            String uri = theRequest.getURI().toString();
            requestLog.append("URL: ").append(uri).append("\n");

        });
        request.onRequestHeaders(theRequest ->
        {
            for (HttpField header : theRequest.getHeaders())
                requestLog.append("HEADER: ").append(header.getName()).append(" -> ")
                        .append(header.getValue()).append("\n");
        });
        request.onRequestContent((theRequest, content) ->
        {
            String contentString = getStringFromByteBuffer(content);

            requestLog.append("CONTENT: ").append(contentString).append("\n");

        });
    }

    private void logResponse(Request request, StringBuilder requestLog)
    {
        StringBuilder responseLog = new StringBuilder("\n");
        request.onResponseHeaders(theResponse ->
        {
            for (HttpField header : theResponse.getHeaders())
                responseLog.append("HEADER: ").append(header.getHeader()).append(" -> ")
                        .append(header.getValue()).append("\n");

            responseLog.append("RESPONSE CONTENT: ");
        });
        request.onResponseContent((theResponse, content) ->
                responseLog.append(getStringFromByteBuffer(content)));
        request.onResponseSuccess(theResponse ->
        {
            responseLog.append("\nSTATUS: ").append(theResponse.getStatus());
            log.info(requestLog.toString());
            log.info(responseLog.toString());
        });
        request.onResponseFailure((theResponse, e) ->
        {
            responseLog.append("\nERROR: ").append(e.getMessage()).append("\n");
            responseLog.append("REASON: ").append(theResponse.getReason()).append("\n");

            log.info(requestLog.toString());
            log.error(responseLog.toString());
        });
    }


    private String getStringFromByteBuffer(ByteBuffer byteBuffer)
    {
        return StandardCharsets.UTF_8.decode(byteBuffer).toString();
    }
}