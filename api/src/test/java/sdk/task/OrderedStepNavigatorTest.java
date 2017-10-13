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

package sdk.task;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.sagebionetworks.research.sdk.result.TaskResult;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.step.StepBase;
import org.sagebionetworks.research.sdk.task.navigation.OrderedStepNavigator;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderedStepNavigatorTest {

    private OrderedStepNavigator navigator;
    private List<? extends Step> steps;

    @Before
    public void setUp() throws Exception {
        steps = Lists.newArrayList(
                new StepBase("step1"),
                new StepBase("step2"),
                new StepBase("step3")
        );
        navigator = new OrderedStepNavigator(steps);
    }

    @Test
    public void getNextStep_forNullStep_returnsFirstStep() {
        assertNextStep(null, null, steps.get(0));
    }

    @Test
    public void getNextStep_forFirstStep_returnsSecondStep() {
        assertNextStep(steps.get(0), null, steps.get(1));
    }

    @Test
    public void getNextStep_forSecondStep_returnsThirdStep() {
        assertNextStep(steps.get(1), null, steps.get(2));
    }

    @Test
    public void getNextStep_forThirdStep_returnsNull() {
        assertNextStep(steps.get(2), null, null);
    }

    @Test
    public void getNextStep_forUnknownStep_returnsNull() {
        assertNextStep(new StepBase("otherStep"), null, null);
    }

    private void assertNextStep(Step currentStep, TaskResult currentTaskResult, Step
            expectedNextStep) {
        Step stepResult = navigator.getNextStep(currentStep, currentTaskResult);
        assertEquals(expectedNextStep, stepResult);
    }

    @Test(expected = NullPointerException.class)
    public void getPreviousStep_nullStep_exception() {
        navigator.getPreviousStep(null, null);
    }

    @Test
    public void getPreviousStep_forFirstStep_returnsNull() {
        assertPreviousStep(steps.get(0), null, null);
    }

    @Test
    public void getPreviousStep_forSecondStep_returnsFirstStep() {
        assertPreviousStep(steps.get(1), null, steps.get(0));
    }

    @Test
    public void getPreviousStep_forThirdStep_returnsSecondStep() {
        assertPreviousStep(steps.get(2), null, steps.get(1));
    }

    @Test
    public void getPreviousStep_forUnknownStep_returnsNull() {
        assertPreviousStep(new StepBase("otherStep"), null, null);
    }

    private void assertPreviousStep(Step currentStep, TaskResult currentTaskResult, Step
            expectedPreviousStep) {
        Step stepResult = navigator.getPreviousStep(currentStep, currentTaskResult);
        assertEquals(expectedPreviousStep, stepResult);
    }
}