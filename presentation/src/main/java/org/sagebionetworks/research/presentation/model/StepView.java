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

import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.ryanharter.auto.value.parcel.ParcelAdapter;

import org.sagebionetworks.research.presentation.DisplayString;
import org.sagebionetworks.research.presentation.model.parcelable.ImmutableSetAdapter;
import java.lang.annotation.Retention;

/**
 * Map a {@link Step} to a {@link StepView} when data is moving from the Domain layer to this layer.
 */
public interface StepView extends Parcelable {
    class ImmutableStepActionViewSetAdapter extends ImmutableSetAdapter<StepActionView> {
        @Override
        protected Creator<? extends StepActionView> getCreator() {
            return AutoValue_StepActionView.CREATOR;
        }

        @Override
        protected StepActionView[] getEmptyArray() {
            return new StepActionView[0];
        }
    }

    @Retention(SOURCE)
    @IntDef({NavDirection.SHIFT_LEFT, NavDirection.SHIFT_RIGHT})
    @interface NavDirection {
        int SHIFT_LEFT = 1;
        int SHIFT_RIGHT = -1;
    }

    @Nullable
    DisplayString getDetail();

    @NonNull
    String getIdentifier();

    @NavDirection int getNavDirection();

    @NonNull
    @ParcelAdapter(ImmutableStepActionViewSetAdapter.class)
    ImmutableSet<StepActionView> getStepActionViews();

    @Nullable
    DisplayString getTitle();
}
