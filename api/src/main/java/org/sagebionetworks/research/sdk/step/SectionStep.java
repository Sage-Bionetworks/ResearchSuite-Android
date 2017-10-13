/*
 *    Copyright 2017 Sage Bionetworks
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.sagebionetworks.research.sdk.step;

import java.util.List;

/**
 * Created by liujoshua on 10/4/2017.
 */

/**
 * Defines a logical subgrouping of steps. Examples would be a section in a longer survey or an
 * active step that includes an instruction step, countdown step, and activity step.
 */
public interface SectionStep extends Step {
    /**
     *
     * @return list of the steps in the subgrouping
     */
    List<Step> getSteps();
}
