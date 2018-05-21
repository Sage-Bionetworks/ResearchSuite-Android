/*
 * BSD 3-Clause License
 *
 * Copyright 2018  Sage Bionetworks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1.  Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2.  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3.  Neither the name of the copyright holder(s) nor the names of any contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission. No license is granted to the trademarks of
 * the copyright holders even if such marks are included in this software.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sagebionetworks.research.app;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sagebionetworks.research.presentation.model.TaskView;

import java.util.UUID;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PerformFooTaskTest {
    @Rule
    public ActivityTestRule<PerformTaskActivity> activityRule =
            createTestRuleForTask(TaskView.builder().setIdentifier("foo").build(), null);

    public static ActivityTestRule<PerformTaskActivity> createTestRuleForTask(TaskView taskView, UUID taskRunUUID) {
        return new ActivityTestRule<PerformTaskActivity>(PerformTaskActivity.class) {
            @Override
            protected Intent getActivityIntent() {
                return PerformTaskActivity.createIntent(
                        InstrumentationRegistry.getInstrumentation().getTargetContext(), taskView, taskRunUUID);
            }
        };
    }

    @Test
    public void showsStep() {
        onView(allOf(withId(R.id.title), isDisplayed()))
                .check(matches(withText("Step 1")));

        checkAndClickNext();

        onView(allOf(withId(R.id.title), isDisplayed()))
                .check(matches(withText("How happy are you?")));

        checkAndClickNext();

    }

    private void checkAndClickNext() {
        ViewInteraction nextActionButton = onView(withId(R.id.rs2_step_navigation_action_forward));
        nextActionButton.check(matches(isDisplayed()));
        nextActionButton.perform(click());
    }
}