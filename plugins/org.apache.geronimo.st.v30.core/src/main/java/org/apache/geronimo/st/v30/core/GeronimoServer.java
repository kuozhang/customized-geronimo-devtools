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
package org.apache.geronimo.st.v30.core;

import java.io.File;
import java.text.MessageFormat;

import javax.enterprise.deploy.spi.DeploymentManager;
import javax.enterprise.deploy.spi.factories.DeploymentFactory;

import org.apache.geronimo.deployment.plugin.factories.DeploymentFactoryImpl;
import org.apache.geronimo.deployment.plugin.jmx.JMXDeploymentManager;
import org.apache.geronimo.st.v30.core.GeronimoRuntimeDelegate;
import org.apache.geronimo.st.v30.core.GeronimoServerDelegate;
import org.apache.geronimo.st.v30.core.IGeronimoVersionHandler;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.util.SocketUtil;

/**
 * <b>GeronimoServer</b> is the implementation of the Geronimo Server Configuration defined in the
 * org.apache.geronimo.st.v30.core plugin (i.e., it extends the 
 * <code>org.eclipse.wst.server.core.serverTypes</code> extension point using the <code>class</code> attribute)
 * 
 * <p>One of the primary functions of <b>GeronimoServer</b> is to persist the state of the Geronimo server across
 * workbench sessions by using various attributes 
 * 
 * @see org.apache.geronimo.st.v30.core.GeronimoServerDelegate
 * 
 * @version $Rev$ $Date$
 */
public class GeronimoServer extends GeronimoServerDelegate {

    private static IGeronimoVersionHandler versionHandler = null;

    private static DeploymentFactory deploymentFactory;

    static {
        deploymentFactory = new DeploymentFactoryImpl();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.st.v30.core.GenericGeronimoServer#getContextRoot(org.eclipse.wst.server.core.IModule)
     */
    public String getContextRoot(IModule module) throws Exception {
        return GeronimoUtils.getContextRoot(module);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#getDeployerURL()
     */
    public String getDeployerURL() {
        return "deployer:geronimo:jmx://" + getServer().getHost() + ":" + getRMINamingPort();
    }

    @Override
    public String getVMArgs() {
        String superVMArgs = super.getVMArgs();
        if (superVMArgs != null && superVMArgs.trim().length() > 0) {
            return superVMArgs;
        }

        String runtimeLocation = getServer().getRuntime().getLocation().toString();
        GeronimoRuntimeDelegate geronimoRuntimeDelegate = (GeronimoRuntimeDelegate) getServer().getRuntime().getAdapter(GeronimoRuntimeDelegate.class);
        if (geronimoRuntimeDelegate == null) {
            geronimoRuntimeDelegate = (GeronimoRuntimeDelegate) getServer().getRuntime().loadAdapter(GeronimoRuntimeDelegate.class,new NullProgressMonitor());
        }
        IVMInstall vmInstall = geronimoRuntimeDelegate.getVMInstall();

        LibraryLocation[] libLocations = JavaRuntime.getLibraryLocations(vmInstall);
        IPath vmLibDir = null;
        for(int i = 0; i < libLocations.length; i++) {
            LibraryLocation loc = libLocations[i];
            IPath libDir = loc.getSystemLibraryPath().removeLastSegments(2);
            if(libDir.toOSString().endsWith("lib")) {
                vmLibDir = libDir;
                break;
            }
        }

        String cp = System.getProperty("path.separator");

        //-javaagent:"GERONIMO_BASE/bin/jpa.jar"
        String javaagent = "";
        File jpaJar = new File(runtimeLocation + "/bin/jpa.jar");
        if (jpaJar.exists()) {
            javaagent = "-javaagent:\"" + runtimeLocation + "/bin/jpa.jar\"";
        }

        //-Djava.ext.dirs="GERONIMO_BASE/lib/ext;JRE_HOME/lib/ext"
        String javaExtDirs = "-Djava.ext.dirs=\"" + runtimeLocation + "/lib/ext" + cp + vmLibDir.append("ext").toOSString() + "\"";

        //-Djava.endorsed.dirs="GERONIMO_BASE/lib/endorsed;JRE_HOME/lib/endorsed"
        String javaEndorsedDirs = "-Djava.endorsed.dirs=\"" + runtimeLocation + "/lib/endorsed" + cp + vmLibDir.append("endorsed").toOSString() + "\"";

        // Specify the minimum memory options for the Geronimo server
        String memoryOpts = "-Xms256m -Xmx512m -XX:MaxPermSize=128m";

        // Specify GERONIMO_BASE
        String homeDirectory = "-Dorg.apache.geronimo.home.dir=\"" + runtimeLocation + "\"";
        
        // Karaf arguments
        String serverLocation = getServer().getRuntime().getLocation().toOSString();
        String karafArgs = "-Dkaraf.startLocalConsole=false -Dkaraf.startRemoteShell=true " + MessageFormat.format("-Dorg.apache.geronimo.home.dir=\"{0}\" -Dkaraf.home=\"{0}\" -Dkaraf.base=\"{0}\" -Djava.util.logging.config.file={0}/etc/java.util.logging.properties", serverLocation);

        StringBuilder vmArgs = new StringBuilder(javaagent);
        vmArgs.append(" ").append(javaExtDirs).append(" ").append(javaEndorsedDirs).append(" ").append(memoryOpts).append(" ").append(homeDirectory).append(" ").append(karafArgs);
        
        return vmArgs.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#getJMXServiceURL()
     */
    public String getJMXServiceURL() {
        String host = getServer().getHost();
        return "service:jmx:rmi://" + host + "/jndi/rmi://" + host + ":" + getRMINamingPort() + "/JMXConnector";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#getJSR88DeployerJar()
     */
    public IPath getJSR88DeployerJar() {
        return getServer().getRuntime().getLocation().append("/lib/jsr88-deploymentfactory.jar");
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#getDeploymentFactory()
     */
    public DeploymentFactory getDeploymentFactory() {
        return deploymentFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#configureDeploymentManager(javax.enterprise.deploy.spi.DeploymentManager)
     */
    public void configureDeploymentManager(DeploymentManager dm) {
        ((JMXDeploymentManager) dm).setLogConfiguration(true, true);
        boolean enableInPlace = SocketUtil.isLocalhost(getServer().getHost()) && isRunFromWorkspace();
        setInPlaceDeployment(dm, enableInPlace);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.geronimo.st.v30.core.IGeronimoServer#getVersionHandler()
     */
    public IGeronimoVersionHandler getVersionHandler() {
        if (versionHandler == null)
            versionHandler = new GeronimoVersionHandler();
        return versionHandler;
    }

    public void setInPlaceDeployment(DeploymentManager dm, boolean enable) {
        ((JMXDeploymentManager) dm).setInPlace(enable);
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.st.v30.core.GeronimoServerDelegate#setDefaults(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void setDefaults(IProgressMonitor monitor) {
        super.setDefaults(monitor);
    }

}