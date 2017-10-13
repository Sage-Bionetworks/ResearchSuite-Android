package org.sagebionetworks.research.sdk.performtask.step.ui;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.google.common.collect.Maps;

import org.sagebionetworks.research.sdk.R;
import org.sagebionetworks.research.sdk.step.Step;
import org.sagebionetworks.research.sdk.step.UIStepBase;
import org.sagebionetworks.research.sdk.step.ui.UIAction;

import java.util.Map;

/**
 * Created by liujoshua on 10/13/2017.
 */

public class InstructionUIStep extends UIStepBase {

    public InstructionUIStep(@NonNull String identifier, int title, int text, int detail, int
            footnote, int imageBefore, int imageAfter, @NonNull Map<String, UIAction> uiActions,
                             @Nullable Step nextStep) {
        super(identifier, title, text, detail, footnote, imageBefore, imageAfter, uiActions);
    }

    public static class Builder {
        @NonNull
        private final String identifier;
        @StringRes
        private int title = R.string.rsi_empty;
        @StringRes
        private int text = R.string.rsi_empty;
        @StringRes
        private int detail = R.string.rsi_empty;
        @StringRes
        private int footnote = R.string.rsi_empty;
        @DrawableRes
        private int imageBefore = R.drawable.rsi_empty;
        @DrawableRes
        private int imageAfter = R.drawable.rsi_empty;
        @NonNull
        private final Map<String, UIAction> uiActions = Maps.newHashMap();
        @Nullable
        private Step nextStep;

        public Builder(@NonNull String identifier) {
            this.identifier = identifier;
        }

        void withTitle(@StringRes int title) {
            this.title = title;
        }

        public void withText(int text) {
            this.text = text;
        }

        public void withDetail(int detail) {
            this.detail = detail;
        }

        public void withFootnote(int footnote) {
            this.footnote = footnote;
        }

        public void withImageBefore(int imageBefore) {
            this.imageBefore = imageBefore;
        }

        public void withImageAfter(int imageAfter) {
            this.imageAfter = imageAfter;
        }

        public void withNextStep(@Nullable Step nextStep) {
            this.nextStep = nextStep;
        }

        public InstructionUIStep build() {
            return new InstructionUIStep(identifier, title, text, detail, footnote, imageBefore,
                    imageAfter, uiActions, nextStep);
        }
    }
}
