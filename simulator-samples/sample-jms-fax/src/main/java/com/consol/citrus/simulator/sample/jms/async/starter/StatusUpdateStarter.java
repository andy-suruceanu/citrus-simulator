/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.simulator.sample.jms.async.starter;

import com.consol.citrus.simulator.jms.SimulatorJmsAsyncScenario;
import com.consol.citrus.simulator.model.ScenarioParameter;
import com.consol.citrus.simulator.sample.jms.async.variables.ReferenceId;
import com.consol.citrus.simulator.sample.jms.async.variables.Status;
import com.consol.citrus.simulator.sample.jms.async.variables.StatusMessage;
import com.consol.citrus.simulator.sample.model.xml.fax.FaxStatusEnumType;
import com.consol.citrus.simulator.scenario.Scenario;
import com.consol.citrus.simulator.scenario.ScenarioStarter;

import java.util.ArrayList;
import java.util.List;

/**
 * This scenario can be used for sending a status message. It can be triggered directly from the simulator GUI.
 *
 * @author Martin Maher
 */
@Scenario("UpdateFaxStatus")
public class StatusUpdateStarter extends SimulatorJmsAsyncScenario implements ScenarioStarter {
    @Override
    protected void configure() {
        echo("Sending Status Message:  ${status}");

        scenario()
                .send()
                .payload("<StatusUpdateMessage xmlns=\"http://citrusframework.org/schemas/fax\">" +
                        "   <referenceId>${referenceId}</referenceId>" +
                        "   <status>${status}</status>\n" +
                        "   <statusMessage>${statusMessage}</statusMessage>\n" +
                        "</StatusUpdateMessage>");
        echo("Done");
    }

    @Override
    public List<ScenarioParameter> getScenarioParameters() {
        List<ScenarioParameter> scenarioParameters = new ArrayList<>();
        scenarioParameters.add(new ReferenceId().asScenarioParameter());
        scenarioParameters.add(new Status(FaxStatusEnumType.SUCCESS).asScenarioParameter());
        scenarioParameters.add(new StatusMessage("").asScenarioParameter());
        return scenarioParameters;
    }

    @Override
    public void setBeanName(String name) {

    }
}