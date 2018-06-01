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

import static com.google.common.base.Preconditions.checkNotNull;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Optional;

import org.sagebionetworks.research.domain.mobile_ui.R;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment;
import org.sagebionetworks.research.mobile_ui.show_step.view.view_binding.StepViewBinding;
import org.sagebionetworks.research.mobile_ui.widget.ActionButton;
import org.sagebionetworks.research.presentation.ActionType;
import org.sagebionetworks.research.presentation.DisplayString;
import org.sagebionetworks.research.presentation.model.StepView;
import org.sagebionetworks.research.presentation.perform_task.PerformTaskViewModel;
import org.sagebionetworks.research.presentation.show_step.ShowStepViewModel;
import org.sagebionetworks.research.presentation.show_step.ShowStepViewModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A ShowStepFragmentBase implements the functionality common to showing all step fragments in terms of 2 other
 * unknown operations (instantiateBinding, getLayoutID).
 *
 * @param <S>
 *         The type of StepView that this fragment uses.
 * @param <VM>
 *         The type of StepViewModel that this fragment uses.
 */
public abstract class ShowStepFragmentBase<S extends StepView, VM extends ShowStepViewModel<? super S>, SB extends StepViewBinding>
        extends Fragment {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowStepFragmentBase.class);

    private static final String ARGUMENT_STEP_VIEW = "STEP_VIEW";

    @Inject
    protected PerformTaskFragment performTaskFragment;

    protected PerformTaskViewModel performTaskViewModel;

    protected VM showStepViewModel;

    @Inject
    protected ShowStepViewModelFactory showStepViewModelFactory;

    protected S stepView;

    protected SB stepViewBinding;

    private Unbinder stepViewUnbinder;

    /**
     * Creates a Bundle containing the given StepView.
     *
     * @param stepView
     *         The StepView to put in the bundle.
     * @return a Bundle containing the given StepView.
     */
    public static Bundle createArguments(@NonNull StepView stepView) {
        checkNotNull(stepView);

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_STEP_VIEW, stepView);
        return args;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);

        // gets the PerformTaskViewModel instance of performTaskFragment
        this.performTaskViewModel = ViewModelProviders.of(this.performTaskFragment).get(PerformTaskViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        S stepViewArg = null;
        if (getArguments() != null) {
            stepViewArg = this.getArguments().getParcelable(ARGUMENT_STEP_VIEW);
        }

        //noinspection unchecked
        this.showStepViewModel = (VM) ViewModelProviders
                .of(this, this.showStepViewModelFactory.create(this.performTaskViewModel, stepViewArg))
                .get(stepViewArg.getIdentifier(), this.showStepViewModelFactory.getViewModelClass(stepViewArg));
        this.showStepViewModel.getStepView().observe(this, this.stepViewBinding::update);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(this.getLayoutId(), container, false);
        this.stepViewBinding = this.instantiateBinding();
        this.stepViewUnbinder = ButterKnife.bind(this.stepViewBinding, view);
        this.stepViewBinding.setActionButtonClickListener(this::handleActionButtonClick);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.stepViewUnbinder.unbind();
    }

    // TODO: make this a util
    public void updateTextView(@NonNull TextView textView, @Nullable DisplayString displayString) {
        if (displayString == null) {
            textView.setVisibility(View.GONE);
            return;
        }

        textView.setVisibility(View.VISIBLE);
        if (displayString.getDisplayString() != null) {
            textView.setText(displayString.getDisplayString());
            return;
        }
        if (displayString.getDefaultDisplayStringRes() != null) {
            textView.setText(displayString.getDisplayString());
            return;
        }

        textView.setVisibility(View.INVISIBLE);
    }

    /**
     * Returns the ActionType corresponding to the given ActionButton or null if the ActionType cannot be found.
     * Default mapping of button id to ActionType is: rs2_step_navigation_action_forward -> ActionType.Forward
     * rs2_step_navigation_action_backward -> ActionType.Backward rs2_step_navigation_action_skip -> ActionType.Skip
     * rs2_step_header_cancel_button -> ActionType.CANCEL rs2_step_header_info_button -> ActionType.INFO
     *
     * @param actionButton
     *         The ActionButton to get the ActionType for.
     * @return the Actiontype corresponding to the given ActionButton or null if the ActionType cannot be found.
     */
    @Nullable
    @ActionType
    protected String getActionTypeFromActionButton(@NonNull ActionButton actionButton) {
        int actionButtonId = actionButton.getId();

        if (R.id.rs2_step_navigation_action_forward == actionButtonId) {
            return ActionType.FORWARD;
        } else if (R.id.rs2_step_navigation_action_backward == actionButtonId) {
            return ActionType.BACKWARD;
        } else if (R.id.rs2_step_navigation_action_skip == actionButtonId) {
            return ActionType.SKIP;
        } else if (R.id.rs2_step_navigation_action_cancel == actionButtonId) {
            return ActionType.CANCEL;
        } else if (R.id.rs2_step_navigation_action_info == actionButtonId) {
            return ActionType.INFO;
        }

        return null;
    }

    /**
     * Returns the layout resource that corresponds to the layout for this fragment.
     *
     * @return the layout resource that corresponds to the layout for this fragment.
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * Called whenever one of this fragment's ActionButton's is clicked. Subclasses should override to correctly
     * handle their ActionButtons.
     *
     * @param actionButton
     *         the ActionButton that was clicked by the user.
     */
    protected void handleActionButtonClick(@NonNull ActionButton actionButton) {
        @ActionType String actionType = this.getActionTypeFromActionButton(actionButton);
        this.showStepViewModel.handleAction(actionType);
    }

    /**
     * Instantiates and returns and instance of the correct type of StepViewBinding for this fragment. Note: If a
     * subclass needs to add any fields to the binding it should override this method to return a different binding.
     *
     * @return An instance of the correct type of StepViewBinding for this fragment.
     */
    @NonNull
    protected abstract SB instantiateBinding();

    @NonNull
    Optional<String> getStringToDisplay(@Nullable DisplayString displayString) {
        if (displayString == null) {
            return Optional.absent();
        }
        if (displayString.getDisplayString() != null) {
            return Optional.of(displayString.getDisplayString());
        }
        if (displayString.getDefaultDisplayStringRes() != null) {
            return Optional.of(this.getString(displayString.getDefaultDisplayStringRes()));
        }

        return Optional.absent();
    }
}
