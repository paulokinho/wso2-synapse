/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *   * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.synapse.experimental.mediators.seda;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.config.xml.AbstractMediatorFactory;

import javax.xml.namespace.QName;
import java.util.Properties;

/**
 *
 */
public class SEDAMediatorFactory extends AbstractMediatorFactory {

    private static final QName SEDA_Q =
            new QName(SynapseConstants.SYNAPSE_NAMESPACE, "seda");

    public Mediator createSpecificMediator(OMElement elem, Properties properties) {

        final SEDAMediator mediator = new SEDAMediator();
        processAuditStatus(mediator, elem);
        String mediatorKey = elem.getAttributeValue(ATT_KEY);

        if (mediatorKey != null && !"".equals(mediatorKey.trim())) {
            mediator.setConsumer(mediatorKey.trim());
        }
        //TODO parse OM element and creates policies
        mediator.setSedaQueueConsumerPolicy(new SEDAQueueConsumerPolicy());
        mediator.setSedaQueueProducerPolicy(new SEDAQueueProducerPolicy());
        mediator.setSedaQueuePolicy(new SEDAQueuePolicy());
        return mediator;
    }

    public QName getTagQName() {
        return SEDA_Q;
    }
}
