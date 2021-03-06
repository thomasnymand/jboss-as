/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.webservices.deployers.deployment;

import static org.jboss.as.webservices.metadata.model.EJBEndpoint.EJB_COMPONENT_VIEW_NAME;
import static org.jboss.as.webservices.util.ASHelper.getJaxrpcEjbs;
import static org.jboss.wsf.spi.deployment.DeploymentType.JAXRPC;
import static org.jboss.wsf.spi.deployment.EndpointType.JAXRPC_EJB21;

import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.webservices.metadata.model.EJBEndpoint;
import org.jboss.wsf.spi.deployment.Deployment;
import org.jboss.wsf.spi.deployment.Endpoint;

/**
 * Creates new JAXRPC EJB21 deployment.
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
final class DeploymentModelBuilderJAXRPC_EJB extends AbstractDeploymentModelBuilder {

    /**
     * Constructor.
     */
    DeploymentModelBuilderJAXRPC_EJB() {
        super(JAXRPC, JAXRPC_EJB21);
    }

    /**
     * Creates new JAXRPC EJB21 deployment and registers it with deployment unit.
     *
     * @param dep webservice deployment
     * @param unit deployment unit
     */
    @Override
    protected void build(final Deployment dep, final DeploymentUnit unit) {
        log.debug("Creating JAXRPC EJB21 endpoints meta data model");
        for (final EJBEndpoint ejbEndpoint : getJaxrpcEjbs(unit)) {
            final String ejbName = ejbEndpoint.getName();
            log.debug("EJB3 name: " + ejbName);
            final String ejbClass = ejbEndpoint.getClassName();
            log.debug("EJB3 class: " + ejbClass);
            final Endpoint ep = newHttpEndpoint(ejbClass, ejbName, dep);
            ep.setProperty(EJB_COMPONENT_VIEW_NAME, ejbEndpoint.getComponentViewName());
        }
    }

}
