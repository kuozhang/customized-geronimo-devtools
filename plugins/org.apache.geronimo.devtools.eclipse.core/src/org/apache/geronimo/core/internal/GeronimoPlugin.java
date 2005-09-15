/**
 * Copyright 2004, 2005 The Apache Software Foundation or its licensors, as applicable
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.geronimo.core.internal;

import org.eclipse.core.runtime.Plugin;

/**
 * The main plugin class.
 */
public class GeronimoPlugin extends Plugin {
    protected static final String PLUGIN_ID = "org.apache.geronimo.devtools.eclipse.core";

    private static GeronimoPlugin singleton;

    /**
     * The constructor.
     */
    public GeronimoPlugin() {
        super();
        singleton = this;
    }

    /**
     * Returns the singleton instance of this plugin.
     * 
     * @return org.eclipse.jst.server.geronimo.core.internal.GeronimoPlugin
     */
    public static GeronimoPlugin getInstance() {
        return singleton;
    }
}