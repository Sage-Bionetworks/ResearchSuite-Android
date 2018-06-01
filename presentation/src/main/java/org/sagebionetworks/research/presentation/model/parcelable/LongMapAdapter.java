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

package org.sagebionetworks.research.presentation.model.parcelable;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.ImmutableLongArray;
import com.ryanharter.auto.value.parcel.TypeAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LongMapAdapter<V extends Parcelable> implements TypeAdapter<ImmutableMap<Long, V>> {
    private static final String MAP_KEYS = "LONG_KEYS";

    @Override
    public ImmutableMap<Long, V> fromParcel(final Parcel in) {
        Bundle longMapBundle = in.readBundle(getClass().getClassLoader());
        long[] longKeys = longMapBundle.getLongArray(MAP_KEYS);
        if (longKeys == null) {
            return null;
        }
        Map<Long, V> longMap = new HashMap<>(longKeys.length);
        for (Long key : longKeys) {
            longMap.put(key, longMapBundle.getParcelable(key.toString()));
        }
        return ImmutableMap.copyOf(longMap);
    }

    @Override
    public void toParcel(final ImmutableMap<Long, V> value, final Parcel dest) {
        Bundle longMapBundle = new Bundle();
        longMapBundle.putLongArray(MAP_KEYS, ImmutableLongArray.copyOf(value.keySet()).toArray());
        for (Entry<Long, ? extends Parcelable> entry : value.entrySet()) {
            longMapBundle.putParcelable(entry.getKey().toString(), entry.getValue());
        }
        dest.writeBundle(longMapBundle);
    }
}
