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

package org.sagebionetworks.research.presentation.util;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;
import org.sagebionetworks.research.presentation.DisplayString;
import org.threeten.bp.Duration;

import java.util.Map;

public class SpokenInstructionUtilTest {
    private final ImmutableMap<String, String> INPUT_MAP = ImmutableMap.<String, String>builder()
            .put("halfway", "Halfway text")
            .put("countdown", "3")
            .put("2", "Text at 2")
            .put("5.5", "Text at 5.5")
            .put("999", "Text at 999")
            .build();

    @Test
    public void convert_NullStepDuration() {
        Map<Duration, DisplayString> result = SpokenInstructionUtil.convert(null, INPUT_MAP);
        assertEquals(3, result.size());
    }

    @Test
    public void convert_StepDuration() {
        Duration stepDuration = Duration.ofSeconds(10);
        Map<Duration, DisplayString> result = SpokenInstructionUtil.convert(stepDuration, INPUT_MAP);
        assertEquals(7, result.size());
        assertEquals("Text at 999", result.get(stepDuration).getDisplayString());
    }

    @Test
    public void convert_StepDurationConflict() {
        Map<Duration, DisplayString> result = SpokenInstructionUtil.convert(Duration.ofSeconds(6), INPUT_MAP);
        assertEquals(0, result.size());
    }

    @Test
    public void convertEntry() {
    }
}