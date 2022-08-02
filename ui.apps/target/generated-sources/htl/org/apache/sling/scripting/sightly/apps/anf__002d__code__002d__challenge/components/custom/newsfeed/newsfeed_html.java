/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.custom.newsfeed;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class newsfeed_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_model = null;
Collection var_collectionvar0_list_coerced$ = null;
_global_model = renderContext.call("use", com.anf.core.models.NewsFeedModel.class.getName(), obj());
out.write("<div>\n    ");
{
    Object var_collectionvar0 = renderContext.getObjectModel().resolveProperty(_global_model, "newsFeedList");
    {
        long var_size1 = ((var_collectionvar0_list_coerced$ == null ? (var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0)) : var_collectionvar0_list_coerced$).size());
        {
            boolean var_notempty2 = (var_size1 > 0);
            if (var_notempty2) {
                {
                    long var_end5 = var_size1;
                    {
                        boolean var_validstartstepend6 = (((0 < var_size1) && true) && (var_end5 > 0));
                        if (var_validstartstepend6) {
                            out.write("<div class=\"grid grid-template-columns-1\">");
                            if (var_collectionvar0_list_coerced$ == null) {
                                var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0);
                            }
                            long var_index7 = 0;
                            for (Object newsfeed : var_collectionvar0_list_coerced$) {
                                {
                                    boolean var_traversal9 = (((var_index7 >= 0) && (var_index7 <= var_end5)) && true);
                                    if (var_traversal9) {
                                        out.write("\n            <div class=\"card\">\n                <img width=\"580\" height=\"300\" class=\"card-img\"");
                                        {
                                            Object var_attrvalue10 = renderContext.getObjectModel().resolveProperty(newsfeed, "image");
                                            {
                                                Object var_attrcontent11 = renderContext.call("xss", var_attrvalue10, "uri");
                                                {
                                                    Object var_shoulddisplayattr13 = ((renderContext.getObjectModel().toBoolean(var_attrcontent11) ? var_attrcontent11 : ("false".equals(var_attrvalue10))));
                                                    if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr13)) {
                                                        out.write(" src");
                                                        {
                                                            boolean var_istrueattr12 = (var_attrvalue10.equals(true));
                                                            if (!var_istrueattr12) {
                                                                out.write("=\"");
                                                                out.write(renderContext.getObjectModel().toString(var_attrcontent11));
                                                                out.write("\"");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        out.write("/>\n                <div class=\"card-content\">\n                    <h2 class=\"card-header\">");
                                        {
                                            Object var_14 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newsfeed, "title"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_14));
                                        }
                                        out.write("</h2>\n                    <p class=\"card-author\"><span class=\"card-author-spec\">Published by: </span>");
                                        {
                                            String var_15 = (" " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newsfeed, "author"), "text")));
                                            out.write(renderContext.getObjectModel().toString(var_15));
                                        }
                                        out.write("</p>\n                    <p class=\"card-text\">");
                                        {
                                            Object var_16 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newsfeed, "description"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_16));
                                        }
                                        out.write("</p>\n                    <p class=\"card-text\">");
                                        {
                                            Object var_17 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newsfeed, "content"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_17));
                                        }
                                        out.write("</p>\n                    <a");
                                        {
                                            Object var_attrvalue18 = renderContext.getObjectModel().resolveProperty(newsfeed, "ctaUrl");
                                            {
                                                Object var_attrcontent19 = renderContext.call("xss", var_attrvalue18, "uri");
                                                {
                                                    Object var_shoulddisplayattr21 = ((renderContext.getObjectModel().toBoolean(var_attrcontent19) ? var_attrcontent19 : ("false".equals(var_attrvalue18))));
                                                    if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr21)) {
                                                        out.write(" href");
                                                        {
                                                            boolean var_istrueattr20 = (var_attrvalue18.equals(true));
                                                            if (!var_istrueattr20) {
                                                                out.write("=\"");
                                                                out.write(renderContext.getObjectModel().toString(var_attrcontent19));
                                                                out.write("\"");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        out.write(" class=\"card-btn\">Read More</a>\n                </div>\n            </div>\n    ");
                                    }
                                }
                                var_index7++;
                            }
                            out.write("</div>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar0_list_coerced$ = null;
}
out.write("\n</div>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

