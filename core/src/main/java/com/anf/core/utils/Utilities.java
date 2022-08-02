package com.anf.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.anf.core.constants.Constants;

public class Utilities {

    public static ResourceResolver getResourceResolver(ResourceResolverFactory resolverFactory) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, Constants.ANF_CODE_CHALLENGE_SUB_SERVICE_NAME);
        ResourceResolver resolver = resolverFactory.getServiceResourceResolver(paramMap);
        return resolver;
    }

    public static String getRequestParameter(SlingHttpServletRequest request, String name) {
        String requestParameter = request.getParameter(name);
        return requestParameter;
    }

}
