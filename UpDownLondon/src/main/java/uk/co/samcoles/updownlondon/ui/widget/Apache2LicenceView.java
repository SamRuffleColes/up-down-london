package uk.co.samcoles.updownlondon.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import uk.co.samcoles.updownlondon.R;

/**
 * Created by sam on 17/02/14.
 */
public class Apache2LicenceView extends FrameLayout {

    public Apache2LicenceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Apache2LicenceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.widget_apache2_licence_view, this, true);

        TextView projectView = (TextView) findViewById(R.id.project);
        TextView copyrightView = (TextView) findViewById(R.id.copyright);

        String projectTitle = "";
        String copyrightNotice = "";

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FLOSSLicence, 0, 0);
        try {
            projectTitle = a.getString(R.styleable.FLOSSLicence_project);
            copyrightNotice = a.getString(R.styleable.FLOSSLicence_copyright);
        } finally {
            a.recycle();
        }

        projectView.setText(projectTitle);
        copyrightView.setText(copyrightNotice);
    }
}
