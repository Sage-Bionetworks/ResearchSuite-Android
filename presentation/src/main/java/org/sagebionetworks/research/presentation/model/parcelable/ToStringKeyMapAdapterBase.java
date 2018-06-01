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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.ImmutableMap;
import com.ryanharter.auto.value.parcel.TypeAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ToStringKeyMapAdapterBase<K, V extends Parcelable> implements TypeAdapter<ImmutableMap<K, V>> {
    @Override
    public ImmutableMap<K, V> fromParcel(final Parcel in) {
        Map<String, V> map = new HashMap<>();
        in.readMap(map, getClass().getClassLoader());

        Map<K, V> outputMap = new HashMap<>();
        for (Entry<String, V> entry : map.entrySet()) {
            outputMap.put(fromString(entry.getKey()), entry.getValue());
        }
        return ImmutableMap.copyOf(outputMap);
    }

    @Override
    public void toParcel(final ImmutableMap<K, V> value, final Parcel dest) {
        Map<String, V> stringMap = new HashMap<>();
        for (Entry<K, V> e : value.entrySet()) {
            stringMap.put(e.getKey().toString(), e.getValue());
        }
        dest.writeMap(stringMap);
    }

    public abstract K fromString(String stringKey);

    public String toStringKey(K key) {
        return key.toString();
    }
}
