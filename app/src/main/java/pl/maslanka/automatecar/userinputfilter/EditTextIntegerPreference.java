package pl.maslanka.automatecar.userinputfilter;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.EditTextPreference;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Artur on 09.11.2016.
 */

public class EditTextIntegerPreference extends EditTextPreference implements DialogInterface.OnClickListener {

    private Integer mInteger;

    public EditTextIntegerPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }

    public EditTextIntegerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }

    public EditTextIntegerPreference(Context context) {
        super(context);
        getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }

    @Override
    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();
        mInteger = parseInteger(text);
        persistString(mInteger != null ? mInteger.toString() : null);
        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) notifyDependencyChange(isBlocking);
    }

    @Override
    public String getText() {
        return mInteger != null ? mInteger.toString() : null;
    }


    private static Integer parseInteger(String text) {
        try { return Integer.parseInt(text); }
        catch (NumberFormatException e) { return null; }
    }

}
