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

package org.sagebionetworks.research.presentation.mapper;

import android.support.annotation.Nullable;

import com.google.common.base.Function;

import org.sagebionetworks.research.domain.step.interfaces.ActiveUIStep;
import org.sagebionetworks.research.domain.step.interfaces.Step;
import org.sagebionetworks.research.presentation.model.ActiveUIStepView;
import org.sagebionetworks.research.presentation.model.BaseStepView;
import org.sagebionetworks.research.presentation.model.StepView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

public class StepMapper implements Function<Step, StepView> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepMapper.class);


    @Inject
    public StepMapper() {
    }

    @Override
    @Nullable
    public StepView apply(@Nullable Step input) {
        if (input == null) {
            return null;
        }
        return BaseStepView.builder()
                .setIdentifier(input.getIdentifier())
                .build();
    }

    ActiveUIStepView apply(ActiveUIStep input) {
        ActiveUIStepView.Builder builder = ActiveUIStepView.builder()
                .setIdentifier(input.getIdentifier());

        Duration stepDuration = null;
        Double durationInSeconds = input.getDuration();
        if (durationInSeconds != null) {
            stepDuration = Duration.ofMillis((long) (1_000L * input.getDuration()));
        }

        Map<Long, String> spokenInstructions = new HashMap<>();
        for (Entry<String, String> entry : input.getSpokenInstructions().entrySet()) {

        }

        return builder.build();
    }

}
