package com.example.miguelmendez.lab_2_3_moviles;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DialogCustom extends DialogFragment {
    public static final String ARGUMENTO_TITULO = "TITLE";
    public static final String ARGUMENTO_FULL_SNIPPET = "FULL_SNIPPET";

    private String titulo;
    private String fullSnippet;

    public DialogCustom() {
    }

    public static DialogCustom newInstance(String title, String fullSnippet) {
        DialogCustom fragment = new DialogCustom();
        Bundle b = new Bundle();
        b.putString(ARGUMENTO_TITULO, title);
        b.putString(ARGUMENTO_FULL_SNIPPET, fullSnippet);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        titulo = args.getString(ARGUMENTO_TITULO);
        fullSnippet = args.getString(ARGUMENTO_FULL_SNIPPET);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialogg = new AlertDialog.Builder(getActivity())
                .setTitle(titulo)
                .setMessage(fullSnippet)
                .create();
        return dialogg;
    }
}
