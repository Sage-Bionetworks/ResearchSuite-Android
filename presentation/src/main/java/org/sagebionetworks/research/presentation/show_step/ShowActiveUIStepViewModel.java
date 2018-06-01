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

package org.sagebionetworks.research.presentation.show_step;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;

import org.sagebionetworks.research.presentation.DisplayString;
import org.sagebionetworks.research.presentation.model.ActiveUIStepView;
import org.sagebionetworks.research.presentation.perform_task.PerformActiveTaskViewModel;
import org.threeten.bp.Duration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class ShowActiveUIStepViewModel<S extends ActiveUIStepView> extends ShowGenericStepViewModel<S> {
    private final CompositeDisposable compositeDisposable;

    private final LiveData<Long> countdownLiveData;

    private final PerformActiveTaskViewModel performActiveTaskViewModel;

    private final LiveData<DisplayString> spokenInstructions;

    private final S stepView;

    private final MutableLiveData<S> stepViewLiveData;

    public ShowActiveUIStepViewModel(PerformActiveTaskViewModel performTaskViewModel, S stepView) {
        super(performTaskViewModel, stepView);

        this.performActiveTaskViewModel = performTaskViewModel;
        this.stepView = stepView;
        this.stepViewLiveData = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();

        stepViewLiveData.setValue(stepView);
        countdownLiveData = LiveDataReactiveStreams.fromPublisher(
                getCountdownObservable().toFlowable(BackpressureStrategy.LATEST));
        spokenInstructions = LiveDataReactiveStreams.fromPublisher(
                getSpokenInstructionsObservable().toFlowable(BackpressureStrategy.BUFFER));
    }

    public LiveData<Long> getCountdown() {
        return countdownLiveData;
    }

    public LiveData<DisplayString> getSpokenInstructions() {
        return spokenInstructions;
    }

    @Override
    public LiveData<S> getStepView() {
        return stepViewLiveData;
    }

    @VisibleForTesting
    Observable<Long> getCountdownObservable() {
        // TODO: countdown based on spokenInstructions, allowing for fractional duration
        Duration duration = stepView.getDuration();
        if (duration == null) {
            return Observable.never();
        }

        return Observable.intervalRange(0, duration.getSeconds(), 0, 1, TimeUnit.SECONDS)
                .map(i -> duration.getSeconds() - i)
                .doOnComplete(performActiveTaskViewModel::goForward);
    }

    @VisibleForTesting
    Observable<DisplayString> getSpokenInstructionsObservable() {
        return Observable.empty();

//        Map<Long, DisplayString> spokenInstructions = stepView.getSpokenInstructions();
//        if (spokenInstructions == null) {
//            return Observable.empty();
//        }
//        return Observable.interval(1, TimeUnit.SECONDS)
//                .map(spokenInstructions::get)
//                .filter(spokenInstruction -> spokenInstruction != null);
    }
}