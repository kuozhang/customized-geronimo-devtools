/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.geronimo.st.core.jaxb;

import javax.xml.bind.Marshaller;

import org.apache.geronimo.jee.naming.GbeanLocator;
import org.apache.geronimo.jee.naming.Pattern;
import org.apache.geronimo.jee.security.Security;
import org.apache.geronimo.jee.web.WebApp;

public class MarshallerListener extends Marshaller.Listener{

	@Override
	public void beforeMarshal(Object source) {
		if (source instanceof Security) {
			Security security = (Security)source;
			if (security.getRoleMappings() != null && security.getRoleMappings().getRole().size() == 0) {
				security.setRoleMappings(null);
			}
		} else if (source instanceof WebApp) {
			WebApp webapp = (WebApp)source;
			GbeanLocator gbeanlocator = webapp.getWebContainer();
			if (isEmpty(gbeanlocator.getGbeanLink()) && isEmpty(gbeanlocator.getPattern())) {
				webapp.setWebContainer(null);
			}
		}
	}
	
	private boolean isEmpty(Pattern pattern) {
		if ( pattern == null ) {
			return true;
		}
		if ( ( pattern.getGroupId() == null || pattern.getGroupId().trim().equals("") ) &&
			 ( pattern.getArtifactId() == null || pattern.getArtifactId().trim().equals("") ) &&
			 ( pattern.getModule() == null || pattern.getModule().trim().equals("") ) &&
			 ( pattern.getName() == null || pattern.getName().trim().equals("") ) &&
			 ( pattern.getVersion() == null || pattern.getVersion().trim().equals("") ) ) {
			return true;
		}
		return false;
	}
	
	private boolean isEmpty(String value) {
		return (value == null || value.trim().equals(""));
	}
}
