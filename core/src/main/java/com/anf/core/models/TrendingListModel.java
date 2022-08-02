package com.anf.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.dataobjects.PageVO;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

@Model(adaptables = { SlingHttpServletRequest.class, Page.class, Resource.class })
public class TrendingListModel {

    private static final Logger LOG = LoggerFactory.getLogger(TrendingListModel.class);

    @Inject
    private ResourceResolver resourceResolver;

    @OSGiService
    @Required
    private QueryBuilder queryBuilder;

    @Inject
    @Via("resource")
    @Default(values = "JCR-SQL2")
    private String queryType;

    @Inject
    @Via("resource")
    @Default(values = "/content/anf-code-challenge/us/en")
    private String defaultPath;

    private List<PageVO> pageList;

    @PostConstruct
    protected void init() {

        LOG.info("TrendingListModel component queryType value: " + queryType);
        LOG.info("TrendingListModel component defaultPath value: " + defaultPath);

        if (queryType == "JCR-SQL2") {
            pageList = searchResultSQL2(defaultPath);
        } else {
            pageList = searchResultQueryBuilder(defaultPath);
        }
        if (pageList != null) {
            LOG.info("Component initialization successful for {} at content path {} were {} results.", queryType,
                    defaultPath, pageList.size());
        } else {
            LOG.error("Component initialization unsuccessful.");
        }
    }

    public List<PageVO> searchResultQueryBuilder(String searchPath) {
        List<PageVO> pageList = new ArrayList<PageVO>();
        try {
            if (resourceResolver != null) {

                final Map<String, String> predicate = new HashMap<String, String>();
                predicate.put("path", searchPath);
                predicate.put("type", "cq:Page");
                predicate.put("1_property", "jcr:content/anfCodeChallenge");
                predicate.put("1_property.1_value", "true");
                predicate.put("p.limit", "4");

                Session session = resourceResolver.adaptTo(Session.class);
                com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(predicate), session);
                query.setStart(0);
                query.setHitsPerPage(10);

                SearchResult searchResult = query.getResult();
                for (Hit hit : searchResult.getHits()) {
                    PageVO pageVO = new PageVO();
                    pageVO.setPageName(hit.getNode().getName());
                    pageVO.setPagePath(hit.getPath());
                    pageList.add(pageVO);
                }

                LOG.info("QueryBuilder execution completed successfully. Result size: " + pageList.size());
            }
        } catch (RepositoryException RE) {
            LOG.error("Repository exception occurred while getting results using QueryBuilder API.", RE);
        }

        return pageList;
    }

    public List<PageVO> searchResultSQL2(String searchPath) {
        List<PageVO> pageList = new ArrayList<PageVO>();
        try {
            if (resourceResolver != null) {

                String sql2Query = "SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([/content/anf-code-challenge/us/en]) and s.[anfCodeChallenge] is not null";
                Session session = resourceResolver.adaptTo(Session.class);
                QueryManager queryManager = session.getWorkspace().getQueryManager();
                Query query = queryManager.createQuery(sql2Query, Query.JCR_SQL2);
                query.setLimit(10);
                QueryResult results = query.execute();
                NodeIterator nodeIter = results.getNodes();
                while (nodeIter.hasNext()) {
                    Node node = nodeIter.nextNode();
                    PageVO pageVO = new PageVO();
                    pageVO.setPageName(node.getName());
                    pageVO.setPagePath(node.getPath());
                    pageList.add(pageVO);
                }
                LOG.info("JCR2SQL Query execution completed successfully. Result size: " + pageList.size());
            }

        } catch (RepositoryException RE) {
            LOG.error("Repository exception occurred while getting queryManager from session object.", RE);
        }
        return pageList;
    }

    public List<PageVO> getPageList() {
        return pageList;
    }

    public void setPageList(List<PageVO> pageList) {
        this.pageList = pageList;
    }
}
