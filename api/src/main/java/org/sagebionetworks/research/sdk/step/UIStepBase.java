package org.sagebionetworks.research.sdk.step;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import org.sagebionetworks.research.sdk.step.ui.UIAction;
import org.sagebionetworks.research.sdk.step.ui.UIStep;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liujoshua on 10/13/2017.
 */

public class UIStepBase extends StepBase implements UIStep {
    @StringRes
    private final int title;
    @StringRes
    private final int text;
    @StringRes
    private final int detail;
    @StringRes
    private final int footnote;
    @DrawableRes
    private final int imageBefore;
    @DrawableRes
    private final int imageAfter;
    @NonNull
    private final Map<String, UIAction> uiActions;

    public UIStepBase(@NonNull String identifier, @StringRes int title, @StringRes int text,
                      @StringRes int detail, @StringRes int footnote,
                      @DrawableRes int imageBefore, @DrawableRes int imageAfter,
                      @NonNull Map<String, UIAction> uiActions) {
        super(identifier);
        checkNotNull(uiActions);

        this.title = title;
        this.text = text;
        this.detail = detail;
        this.footnote = footnote;
        this.imageBefore = imageBefore;
        this.imageAfter = imageAfter;
        this.uiActions = uiActions;
    }

    @Override
    public UIAction getAction(@NonNull @UIAction.UIActionType String actionType) {
        return uiActions.get(actionType);
    }

    @Override
    public boolean shouldHideAction(@NonNull @UIAction.UIActionType String actionType) {
        return uiActions.get(actionType) == null;
    }

    @Override
    public int getTitle() {
        return title;
    }

    @Override
    public int getText() {
        return text;
    }

    @Override
    public int getDetail() {
        return detail;
    }

    @Override
    public int getFootnote() {
        return footnote;
    }

    @Override
    public int getImageBefore() {
        return imageBefore;
    }

    @Override
    public int getImageAfter() {
        return imageAfter;
    }
}
