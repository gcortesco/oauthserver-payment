package com.riu.payment.oauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riu.payment.model.OauthRequest;


import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomWrappedRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private OauthRequest oauthRequest;
    ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Create a new request wrapper that will merge additional parameters into
     * the request object without prematurely reading parameters from the
     * original request.
     *
     * @param request
     *
     */
    public CustomWrappedRequest (final HttpServletRequest request)
    {
        super(request);
        this.request = request;
        oauthRequest = getOauthRequest();
    }

    @Override
    public String getParameter(final String name)
    {
        String[] strings = getParameterMap().get(name);
        if (strings != null)
        {
            return strings[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> allParameters =  new TreeMap<String, String[]>();
        allParameters.putAll(super.getParameterMap());
        if (oauthRequest != null) {
            allParameters.put("client_id", new String[]{oauthRequest.getClient_id()});
            allParameters.put("client_secret", new String[]{oauthRequest.getClient_secret()});
            allParameters.put("grant_type", new String[]{oauthRequest.getGrant_type()});
        }
        //Return an unmodifiable collection because we need to uphold the interface contract.
        return Collections.unmodifiableMap(allParameters);
    }

    private OauthRequest getOauthRequest(){
        String body = null;
        OauthRequest oauthRequest = null;
        try {
            if ("POST".equalsIgnoreCase(request.getMethod()) && request.getContentLength() > 0) {
                body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                oauthRequest = objectMapper.readValue(body, OauthRequest.class);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return oauthRequest;
    }

    @Override
    public Enumeration<String> getParameterNames()
    {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name)
    {
        return getParameterMap().get(name);
    }
}
