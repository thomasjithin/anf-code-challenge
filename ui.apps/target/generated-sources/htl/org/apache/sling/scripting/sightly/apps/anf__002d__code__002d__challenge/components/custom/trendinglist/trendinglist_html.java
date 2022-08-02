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
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.custom.trendinglist;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class trendinglist_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _dynamic_properties = bindings.get("properties");
Object _global_model = null;
Collection var_collectionvar1_list_coerced$ = null;
out.write("<h3>");
{
    String var_0 = ("Trending List Component based on " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "queryType"), "text")));
    out.write(renderContext.getObjectModel().toString(var_0));
}
out.write("</h3>\n\n");
_global_model = renderContext.call("use", com.anf.core.models.TrendingListModel.class.getName(), obj());
out.write("<div>\n    ");
{
    Object var_collectionvar1 = renderContext.getObjectModel().resolveProperty(_global_model, "pageList");
    {
        long var_size2 = ((var_collectionvar1_list_coerced$ == null ? (var_collectionvar1_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar1)) : var_collectionvar1_list_coerced$).size());
        {
            boolean var_notempty3 = (var_size2 > 0);
            if (var_notempty3) {
                {
                    long var_end6 = var_size2;
                    {
                        boolean var_validstartstepend7 = (((0 < var_size2) && true) && (var_end6 > 0));
                        if (var_validstartstepend7) {
                            out.write("<ul class=\"trendinglist__list\">");
                            if (var_collectionvar1_list_coerced$ == null) {
                                var_collectionvar1_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar1);
                            }
                            long var_index8 = 0;
                            for (Object page : var_collectionvar1_list_coerced$) {
                                {
                                    boolean var_traversal10 = (((var_index8 >= 0) && (var_index8 <= var_end6)) && true);
                                    if (var_traversal10) {
                                        out.write("\n        <li>        \n             <a");
                                        {
                                            String var_attrcontent11 = (("`" + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(page, "pagePath"), "uri"))) + ".html`");
                                            {
                                                Object var_shoulddisplayattr12 = ((renderContext.getObjectModel().toBoolean(var_attrcontent11) ? var_attrcontent11 : ("false".equals(var_attrcontent11))));
                                                if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr12)) {
                                                    out.write(" href=\"");
                                                    out.write(renderContext.getObjectModel().toString(var_attrcontent11));
                                                    out.write("\"");
                                                }
                                            }
                                        }
                                        out.write(">");
                                        {
                                            Object var_13 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(page, "pageName"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_13));
                                        }
                                        out.write("</a>\n            <p>");
                                        {
                                            String var_14 = (("[" + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(page, "pagePath"), "text"))) + "]");
                                            out.write(renderContext.getObjectModel().toString(var_14));
                                        }
                                        out.write("</p>\n        </li>\n    ");
                                    }
                                }
                                var_index8++;
                            }
                            out.write("</ul>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar1_list_coerced$ = null;
}
out.write("\n</div>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

