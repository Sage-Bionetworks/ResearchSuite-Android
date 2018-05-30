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

package org.sagebionetworks.research.mobile_ui.show_step.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sagebionetworks.research.domain.mobile_ui.R;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment;
import org.sagebionetworks.research.mobile_ui.show_step.view.view_binding.UIStepViewBinding;
import org.sagebionetworks.research.presentation.model.StepView;
import org.sagebionetworks.research.presentation.perform_task.PerformTaskViewModel;
import org.sagebionetworks.research.presentation.show_step.ShowStepViewModel;
import org.sagebionetworks.research.presentation.show_step.ShowStepViewModelFactory;


import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class ShowUIStepFragmentBase<S extends StepView, VM extends ShowStepViewModel<S>> extends Fragment {
    public static final String ARGUMENT_STEP_VIEW = "UI_STEP_VIEW";

    private UIStepViewBinding binding;
    @Inject
    PerformTaskFragment performTaskFragment;
    @Inject
    ShowStepViewModelFactory showStepViewModelFactory;
    private PerformTaskViewModel performTaskViewModel;
    private S stepView;
    private VM showStepViewModel;

    public static Bundle createArguments(@NonNull StepView stepView) {
        checkNotNull(stepView);
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_STEP_VIEW, stepView);
        return args;
    }

    public int getLayoutId() {
        return R.layout.mpower2_instruction_step;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        // gets the PerformTaskViewModel instance of performTaskFragment
        performTaskViewModel = ViewModelProviders.of(performTaskFragment).get(PerformTaskViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        S stepViewArg = null;
        if (this.getArguments() != null) {
            stepViewArg = this.getArguments().getParcelable(ARGUMENT_STEP_VIEW);
        }

        // noinspection unchecked
        this.showStepViewModel = (VM) ViewModelProviders.of(this,
                this.showStepViewModelFactory.create(this.performTaskViewModel, stepViewArg))
                .get(stepViewArg.getIdentifier(), this.showStepViewModelFactory.getViewModelClass(stepViewArg));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        this.binding = new UIStepViewBinding(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding.unbind();
    }

    public UIStepViewBinding getBinding() {
        return this.binding;
    }
}
