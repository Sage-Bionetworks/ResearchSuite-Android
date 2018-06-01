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

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Sets;

import org.sagebionetworks.research.domain.step.interfaces.ActiveUIStep.SpokenInstructionKey;
import org.sagebionetworks.research.presentation.DisplayString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Duration;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SpokenInstructionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpokenInstructionUtil.class);

    private static final ImmutableSet<String> SPOKEN_INSTRUCTION_KEYS;

    /**
     * Converts spoken instructions from the domain layer to the presentation layer.
     *
     * @param stepDuration
     *         duration to run the step, if applicable
     * @param spokenInstructions
     *         spoken instructions convertEntry from the domain layer
     * @return spoken instructions convertEntry for the presentation layer
     */
    @NonNull
    public static ImmutableMap<Duration, DisplayString> convert(@Nullable Duration stepDuration,
            @NonNull Map<String, String> spokenInstructions) {
        checkNotNull(spokenInstructions);

        ImmutableMap.Builder<Duration, DisplayString> builder = ImmutableMap.builder();
        for (Entry<String, String> entry : spokenInstructions.entrySet()) {
            builder.putAll(convertEntry(stepDuration, entry));
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Exception while converting spoken instructions: ", e);
            return ImmutableMap.of();
        }
    }

    private SpokenInstructionUtil() {
        // private constructor for private util class
    }

    @VisibleForTesting
    static Set<Entry<Duration, DisplayString>> convertEntry(@Nullable Duration stepDuration,
            @NonNull Entry<String, String> spokenInstruction) {

        String spokenInstructionKey = spokenInstruction.getKey();
        if (SPOKEN_INSTRUCTION_KEYS.contains(spokenInstructionKey)) {
            SpokenInstructionKey key = SpokenInstructionKey.valueOf(spokenInstructionKey);

            if (stepDuration == null) {
                LOGGER.warn("No step duration defined, cannot process spokenInstructionKey: {}",
                        spokenInstructionKey);
                return Collections.emptySet();
            }
            if (SpokenInstructionKey.countdown == key) {
                return countdown(stepDuration, spokenInstruction);
            }
            return Sets.newHashSet(
                    new SimpleEntry<>(convertSpokenInstructionKey(stepDuration,
                            SpokenInstructionKey.valueOf(spokenInstructionKey)),
                            DisplayString.create(spokenInstruction.getValue(), null)));
        } else {
            try {
                Double durationInSeconds = Double.parseDouble(spokenInstructionKey);
                Duration duration = toDurationInSeconds(durationInSeconds);
                if (stepDuration != null && duration.compareTo(stepDuration) > 0) {
                    duration = stepDuration;
                }
                return Sets.newHashSet(
                        new SimpleEntry<>(
                                duration,
                                DisplayString.create(spokenInstruction.getValue(), null)));
            } catch (NumberFormatException e) {
                LOGGER.warn("Couldn't parse step duration", e);
                return Collections.emptySet();
            }
        }
    }

    @NonNull
    static Duration convertSpokenInstructionKey(Duration stepDuration,
            @NonNull SpokenInstructionKey spokenInstructionKey) {
        checkNotNull(stepDuration);
        checkNotNull(spokenInstructionKey);

        switch (spokenInstructionKey) {
            case start:
                return Duration.ZERO;
            case halfway:
                return stepDuration.dividedBy(2);
            case end:
                return stepDuration;
        }
        throw new IllegalStateException();
    }

    @VisibleForTesting
    static Set<Entry<Duration, DisplayString>> countdown(@NonNull Duration stepDuration,
            @NonNull Entry<String, String> spokenInstruction) {
        checkNotNull(stepDuration);
        checkNotNull(spokenInstruction);

        Set<Entry<Duration, DisplayString>> counts = new HashSet<>();

        long count = Long.parseLong(spokenInstruction.getValue());
        for (long i = 1; i <= count; i++) {
            Duration countDuration = stepDuration.minusSeconds(i);
            counts.add(new SimpleEntry<>(
                    countDuration,
                    DisplayString.create(Long.toString(i), null)));
        }

        return counts;
    }

    @VisibleForTesting
    static Duration toDurationInSeconds(Double durationInSeconds) {
        return Duration.ofMillis((long) (1_000L * durationInSeconds));
    }

    static {
        ImmutableSet.Builder<String> builder = new Builder<>();
        for (SpokenInstructionKey key : SpokenInstructionKey.values()) {
            builder.add(key.name());
        }
        SPOKEN_INSTRUCTION_KEYS = builder.build();
    }
}
