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

package org.sagebionetworks.research.presentation.inject;

import org.sagebionetworks.research.presentation.model.BaseStepView;
import org.sagebionetworks.research.presentation.model.implementations.ActiveUIStepViewBase;
import org.sagebionetworks.research.presentation.model.implementations.CompletionStepViewBase;
import org.sagebionetworks.research.presentation.model.implementations.CountdownStepViewBase;
import org.sagebionetworks.research.presentation.model.implementations.FormUIStepViewBase;
import org.sagebionetworks.research.presentation.model.implementations.UIStepViewBase;
import org.sagebionetworks.research.presentation.model.interfaces.FormUIStepView;
import org.sagebionetworks.research.presentation.model.interfaces.StepView;
import org.sagebionetworks.research.presentation.show_step.ShowGenericStepViewModelFactory;
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowStepViewModelFactory;
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowActiveUIStepViewModelFactory;
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.AbstractShowStepViewModelFactory;
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowUIStepViewModelFactory;

import java.util.Map;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class ShowStepViewModelModule {
    @MapKey
    public @interface StepViewClassKey {
        String value();
    }

    @Provides
    @IntoMap
    @StepViewClassKey(ActiveUIStepViewBase.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideActiveUIStepVMF() {
        return new ShowActiveUIStepViewModelFactory<>();
    }

    // TODO: rkolmos 06/01/2018 Implement a view model for FormUISteps.
    @Provides
    @IntoMap
    @StepViewClassKey(FormUIStepViewBase.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideFormUIStepVMF() {
        return new ShowUIStepViewModelFactory<FormUIStepView>();
    }

    @Provides
    @IntoMap
    @StepViewClassKey(BaseStepView.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideGenericStepVMF() {
        return new ShowGenericStepViewModelFactory();
    }

    @Provides
    static AbstractShowStepViewModelFactory provideShowStepViewModelFactory(
            Map<String, ShowStepViewModelFactory<?, ? extends StepView>> t) {
        return new AbstractShowStepViewModelFactory(t);
    }

    @Provides
    @IntoMap
    @StepViewClassKey(UIStepViewBase.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideUIStepVMF() {
        return new ShowUIStepViewModelFactory<>();
    }

    @Provides
    @IntoMap
    @StepViewClassKey(CompletionStepViewBase.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideCompletionStepVMF() {
        return new ShowUIStepViewModelFactory<>();
    }

    @Provides
    @IntoMap
    @StepViewClassKey(CountdownStepViewBase.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> provideCountdownStepVMF() {
        return new ShowActiveUIStepViewModelFactory<>();
    }
}
