/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.anf.core.servlets;

import com.anf.core.constants.Constants;
import com.anf.core.dataobjects.UserVO;
import com.anf.core.services.ContentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/saveUserDetails")
public class UserServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    @Reference
    private ContentService contentService;

    @Override
    protected void doPost(final SlingHttpServletRequest slingRequest,
            final SlingHttpServletResponse slingResponse) throws ServletException, IOException {

        JSONObject responseJSON = null;
        PrintWriter out = slingResponse.getWriter();
        slingResponse.setContentType("application/json");
        slingResponse.setCharacterEncoding("UTF-8");

        try {

            UserVO userVO = getInputParameters(slingRequest);
            responseJSON = contentService.validateNewUserDetails(userVO);
            if (responseJSON == null) {
                responseJSON = new JSONObject();
                if (contentService.commitUserDetails(userVO)) {
                    responseJSON.put("status", "success");
                } else {
                    responseJSON.put("status", "error");
                    responseJSON.put("message", "User already exists.");
                }
            }
            // Nothing to handle in else case, responseJSON will contain status and message
            // in case of error.

        } catch (JSONException JSONE) {
            LOG.error("JSON exception in User Servlet", JSONE);
        } catch (Exception ex) {
            LOG.error("Generic exception in User Servlet", ex);
        } finally {
            out.append(responseJSON.toString());
            out.flush();
            out.close();
        }
    }

    private UserVO getInputParameters(SlingHttpServletRequest request) throws NumberFormatException {

        UserVO userVO = new UserVO();

        StringBuffer requestParamBuilder = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                requestParamBuilder.append(line);
            }

            JSONObject requestJSON = new JSONObject(requestParamBuilder.toString());
            userVO.setFirstName(requestJSON.getString(Constants.ADDUSER_SERVLET_FNAME));
            userVO.setLastName(requestJSON.getString(Constants.ADDUSER_SERVLET_LNAME));
            userVO.setCountry(requestJSON.getString(Constants.ADDUSER_SERVLET_COUNTRY));
            userVO.setAge(requestJSON.getInt(Constants.ADDUSER_SERVLET_AGE));

        } catch (JSONException JSONE) {
            LOG.error("JSON Exception occurred while reading input params;", JSONE);
        } catch (IOException IOE) {
            LOG.error("IO Exception occurred while reading input params;", IOE);
        } catch (Exception Ex) {
            LOG.error("Generic Exception occurred while reading input params;", Ex);
        }

        return userVO;
    }
}
