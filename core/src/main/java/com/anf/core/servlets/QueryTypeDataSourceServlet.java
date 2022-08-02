package com.anf.core.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;

import org.apache.sling.api.wrappers.ValueMapDecorator;

@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/datasource/queryTypes")
public class QueryTypeDataSourceServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(QueryTypeDataSourceServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest slingRequest, final SlingHttpServletResponse slingResponse) {
        try {

            slingRequest.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());

            ResourceResolver resourceResolver = slingRequest.getResourceResolver();
            List<Resource> list = new ArrayList<Resource>();

            ValueMap jcrsql2VM = new ValueMapDecorator(new HashMap<String, Object>());
            jcrsql2VM.put("value", "JCR-SQL2");
            jcrsql2VM.put("text", "JCR SQL2 Mode");

            list.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", jcrsql2VM));

            ValueMap queryBuilderVM = new ValueMapDecorator(new HashMap<String, Object>());
            queryBuilderVM.put("value", "QUERYBUILDER");
            queryBuilderVM.put("text", "QueryBuilder Mode");

            list.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", queryBuilderVM));

            DataSource ds = new SimpleDataSource(list.iterator());
            slingRequest.setAttribute(DataSource.class.getName(), ds);

        } catch (Exception ex) {
            LOG.error("Error occurred while create datasource", ex);
        }
        return;
    }

}
