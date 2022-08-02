package com.anf.core.services.impl;

import com.anf.core.constants.Constants;
import com.anf.core.dataobjects.UserVO;
import com.anf.core.services.ContentService;
import com.anf.core.utils.Utilities;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = ContentService.class)
public class ContentServiceImpl implements ContentService {

    private static final Logger LOG = LoggerFactory.getLogger(ContentService.class);

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public JSONObject validateNewUserDetails(UserVO userVO) throws JSONException {

        JSONObject responseJSON = new JSONObject();
        try {
            if (StringUtils.isNotBlank(userVO.getFirstName()) || StringUtils.isNotBlank(userVO.getLastName())
                    || StringUtils.isNotBlank(userVO.getCountry())) {

                int userAge = userVO.getAge();

                if (determineIfAgeValid(userAge)) {
                    return null;
                } else {
                    responseJSON.put("status", "error");
                    responseJSON.put("message", "Sorry, You are not eligible");
                }
            } else {
                responseJSON.put("status", "error");
                responseJSON.put("message", "Mandatory fields are missing");
            }
        } catch (LoginException LE) {
            LOG.error("Login exception occurred while validating new user details", LE);
            responseJSON.put("status", "error");
            responseJSON.put("message", "Generic exception occurred, please contact support");
        } catch (NumberFormatException NFE) {
            LOG.error("Number Format exception occurred while validating new user details", NFE);
            responseJSON.put("status", "error");
            responseJSON.put("message", "Generic exception occurred, please contact support");
        } catch (Exception ex) {
            LOG.error("General exception occurred ", ex);
            responseJSON.put("status", "error");
            responseJSON.put("message", "Generic exception occurred, please contact support");
        }
        return responseJSON;
    }

    private boolean determineIfAgeValid(int age) throws LoginException, NumberFormatException {
        ResourceResolver resourceResolver = Utilities.getResourceResolver(resolverFactory);
        Resource ageValidatorResource = resourceResolver.getResource("/etc/age");
        ValueMap ageValidatorValueMap = ageValidatorResource.getValueMap();
        int minAge = Integer.parseInt((String) ageValidatorValueMap.get("minAge"));
        int maxAge = Integer.parseInt((String) ageValidatorValueMap.get("maxAge"));
        LOG.debug("Min Age: " + minAge);
        LOG.debug("Max Age: " + maxAge);
        if (age <= maxAge && age >= minAge) {
            return Boolean.TRUE.booleanValue();
        }

        return Boolean.FALSE.booleanValue();
    }

    @Override
    public boolean commitUserDetails(UserVO userVO) {

        Boolean userDetailsAdded = Boolean.FALSE;
        String saveNodeLocation = Constants.DEFAULT_SAVE_NODE_LOCATION + Constants.ANF_SAVE_NODE_NAME;
        try {
            ResourceResolver resourceResolver = Utilities.getResourceResolver(resolverFactory);
            Session session = resourceResolver.adaptTo(Session.class);
            if (session.nodeExists(saveNodeLocation)) {
                userDetailsAdded = addUserNode(session, saveNodeLocation, userVO);

            } else {
                addParentNode(session, Constants.ANF_SAVE_NODE_NAME);
                userDetailsAdded = addUserNode(session, saveNodeLocation, userVO);
            }

            LOG.info("Node creation status: " + userDetailsAdded);

        } catch (Exception e) {
            LOG.error("\n Error while creating JCR node - {} ", e.getMessage());
        }

        return userDetailsAdded.booleanValue();
    }

    private boolean addUserNode(Session session, String nodeLocation, UserVO userVO) {
        try {
            Node parentNode = session.getNode(nodeLocation);
            if (!parentNode.hasNode(userVO.getNodeName())) {
                Node userNode = parentNode.addNode(userVO.getNodeName(), Constants.ADDUSER_NODE_TYPE);
                session.save();
                LOG.info("User node {} added successfully at {}" + userNode.getName(), userNode.getPath());
                return Boolean.TRUE;
            }
        } catch (Exception ex) {
            LOG.error("Exception occurred while adding new node.", ex);
        }
        return Boolean.FALSE;
    }

    private String addParentNode(Session session, String nodeName) {
        try {
            if (session.nodeExists(Constants.DEFAULT_SAVE_NODE_LOCATION)) {
                Node parentNode = session.getNode(Constants.DEFAULT_SAVE_NODE_LOCATION);
                Node anfNode = parentNode.addNode(Constants.ANF_SAVE_NODE_NAME);
                session.save();
                return anfNode.getName();

            }
        } catch (RepositoryException e) {
            LOG.error("Repository exception occurred while adding parent node: ", e);
        }

        return null;
    }
}
