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
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.custom.adduserform;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class adduserform_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _dynamic_properties = bindings.get("properties");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n    ");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "all");
    {
        String var_templateoptions1_field$_categories = "anf.components.addUserForm";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n\n\n<form class=\"userForm\">\n    <fieldset>\n        <legend>");
{
    Object var_2 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "title"), "text");
    out.write(renderContext.getObjectModel().toString(var_2));
}
out.write("</legend>\n        <div class=\"error-msg\" id=\"errorMessage\">\n            This is an error message\n        </div>\n        <div class=\"success-msg\" id=\"successMessage\">\n            This is a success message\n        </div>\n        <ul>\n            <li>\n                <label for=\"firstname\">First Name:</label>\n                <input type=\"text\" id=\"firstname\" required/>\n                <span class=\"feedback\"></span>\n            </li>\n            <li>\n                <label for=\"lastname\">Last Name:</label>\n                <input type=\"text\" id=\"lastname\" required/>\n                <span class=\"feedback\"></span>\n            </li>\n            <li>\n                <label for=\"age\">Your age:</label>\n                <input type=\"text\" id=\"age\" required/>\n                <span class=\"feedback\"></span>\n            </li>\n            <li>\n                <label for=\"countries\">Select country:</label>\n                <select id=\"countries\" name=\"countries\"></select>\n                <span class=\"feedback\"></span>\n            </li>\n            <li>\n                <button type=\"submit\" id=\"addUserFormBtn\">Submit</button>\n            </li>\n        </ul>\n    </fieldset>\n</form>\n\n\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

