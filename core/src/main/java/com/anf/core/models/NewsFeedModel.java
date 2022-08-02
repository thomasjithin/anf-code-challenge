package com.anf.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anf.core.dataobjects.NewsFeedVO;
import com.day.cq.wcm.api.Page;

@Model(adaptables = { SlingHttpServletRequest.class, Page.class, Resource.class })
public class NewsFeedModel {
    private static final Logger LOG = LoggerFactory.getLogger(NewsFeedModel.class);

    @Inject
    private ResourceResolver resourceResolver;

    @Inject
    @Via("resource")
    @Default(values = StringUtils.EMPTY)
    private String rootPath;

    private List<NewsFeedVO> newsFeedList;

    @PostConstruct
    protected void init() {
        newsFeedList = new ArrayList<NewsFeedVO>();

        LOG.info("News Feed model component rootPath value: " + rootPath);

        Resource newsFeedResource = resourceResolver.getResource(rootPath);
        if (newsFeedResource != null) {
            Node newsFeedNode = newsFeedResource.adaptTo(Node.class);
            try {
                if (newsFeedNode.hasNodes()) {
                    NodeIterator nodeIter = newsFeedNode.getNodes();
                    while (nodeIter.hasNext()) {
                        Node node = nodeIter.nextNode();
                        NewsFeedVO newsFeedVO = new NewsFeedVO();
                        if (node.hasProperty("title")) {
                            newsFeedVO.setTitle(node.getProperty("title").getString());
                        }
                        if (node.hasProperty("description")) {
                            newsFeedVO.setDescription(node.getProperty("description").getString());
                        }
                        if (node.hasProperty("content")) {
                            newsFeedVO.setContent(node.getProperty("content").getString());
                        }
                        if (node.hasProperty("author")) {
                            newsFeedVO.setAuthor(node.getProperty("author").getString());
                        }
                        if (node.hasProperty("url")) {
                            newsFeedVO.setCtaUrl(node.getProperty("url").getString());
                        }
                        if (node.hasProperty("urlImage")) {
                            newsFeedVO.setImage(node.getProperty("urlImage").getString());
                        }
                        LOG.info("NewsFeedVO content: " + newsFeedVO.toString());
                        newsFeedList.add(newsFeedVO);
                    }

                }
            } catch (RepositoryException e) {
                LOG.error("Repository exception while iterating through news feed", e);
            }
        }
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<NewsFeedVO> getNewsFeedList() {
        return newsFeedList;
    }

    public void setNewsFeedList(List<NewsFeedVO> newsFeedList) {
        this.newsFeedList = newsFeedList;
    }

}
