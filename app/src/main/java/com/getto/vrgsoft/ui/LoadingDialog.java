package com.getto.vrgsoft.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadingDialog extends DialogFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static String messageToDisplay = "";


    private String mMessageToDisplay;

    public static LoadingDialog newInstance(String message) {

        LoadingDialog dialog = new LoadingDialog();
        messageToDisplay = message;
        return dialog;
    }


    @NonNull
    public static LoadingView view(@NonNull final FragmentManager fm) {
        return new LoadingView() {

            private final AtomicBoolean mWaitForHide = new AtomicBoolean();

            @Override
            public void showLoadingIndicator(String message) {
                if (mWaitForHide.compareAndSet(false, true)) {
                    if (fm.findFragmentByTag(LoadingDialog.class.getName()) == null) {
                        DialogFragment dialog = LoadingDialog.newInstance(message);

                        dialog.show(fm, LoadingDialog.class.getName());
                    }
                }
            }

            @Override
            public void hideLoadingIndicator() {
                if (mWaitForHide.compareAndSet(true, false)) {
                    HANDLER.post(new HideTask(fm));
                }
            }
        };
    }

    @NonNull
    public static LoadingView view(@NonNull Fragment fragment) {
        return view(fragment.getFragmentManager());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
      //  View view = inflater.inflate(R.layout.dialog_loading, null);
      //  TextView message = (TextView) (view.findViewById(R.id.loadingText));
      //  message.setText(messageToDisplay);
        return new AlertDialog.Builder(getActivity())
                .setView(null)
                .create();
    }

    private static class HideTask implements Runnable {

        private final Reference<FragmentManager> mFmRef;

        private int mAttempts = 10;

        public HideTask(@NonNull FragmentManager fm) {
            mFmRef = new WeakReference<>(fm);
        }

        @Override
        public void run() {
            HANDLER.removeCallbacks(this);
            final FragmentManager fm = mFmRef.get();
            if (fm != null) {
                final LoadingDialog dialog = (LoadingDialog) fm.findFragmentByTag(LoadingDialog.class.getName());
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss();
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, 300);
                }
            }
        }
    }

}

