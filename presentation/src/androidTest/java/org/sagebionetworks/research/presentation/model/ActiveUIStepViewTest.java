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

package org.sagebionetworks.research.presentation.model;

import static org.junit.Assert.assertEquals;

import android.os.Parcel;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;
import org.sagebionetworks.research.presentation.DisplayString;
import org.sagebionetworks.research.presentation.model.StepView.NavDirection;
import org.threeten.bp.Duration;

public class ActiveUIStepViewTest {

    @Test(expected = IllegalStateException.class)
    public void testBuildFailsWIthNoId() {
        ActiveUIStepView.builder()
                .build();
    }

    @Test
    public void testBuildSucceedsWithOnlyIdProvided() {
        ActiveUIStepView.builder()
                .setIdentifier("id")
                .build();
    }

    @Test
    public void testParcelable() {
        ActiveUIStepView activeUIStepView = ActiveUIStepView.builder()
                .setIdentifier("id")
                .setDetail(DisplayString.create("detail", 5))
                .setNavDirection(NavDirection.SHIFT_RIGHT)
//                .setStepActionViews(ImmutableSet.of(
//                        StepActionView.create(ActionType.FORWARD, 0,
//                                DisplayString.create("forward", R.string.rs2_navigation_action_forward), true, true)))
                .setTitle(DisplayString.create("title", 10))
                .setDuration(Duration.ofSeconds(30))
                .setSpokenInstructions(ImmutableMap.of(1L, DisplayString.create("text1", 9)))
                .build();

        Parcel parcel = Parcel.obtain();
        activeUIStepView.writeToParcel(parcel, activeUIStepView.describeContents());
        parcel.setDataPosition(0);

        ActiveUIStepView createdFromParcel = AutoValue_ActiveUIStepView.CREATOR.createFromParcel(parcel);

        assertEquals(activeUIStepView, createdFromParcel);
    }
}