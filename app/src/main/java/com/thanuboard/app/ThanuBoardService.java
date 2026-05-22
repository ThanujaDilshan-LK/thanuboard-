package com.thanuboard.app;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class ThanuBoardService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;
    
    // බාගත කරගැනීමට දෙන නිවැරදි GitHub Link එක
    private static final String DOWNLOAD_LINK = "https://github.com/ThanujaDilshan-LK/ThanuBoard/releases/latest";
    private static final String SHARE_MESSAGE = "Hey! Download the new ThanuBoard Keyboard from here: " + DOWNLOAD_LINK;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;
                
            // Sriboard එකේ වගේ කීබෝඩ් එකෙන් කෙලින්ම Share කරන්න (Key code එක -100 ලෙස ගමු)
            case -100: 
                shareThanuBoard();
                break;
                
            default:
                char code = (char) primaryCode;
                ic.commitText(String.valueOf(code), 1);
        }
    }

    // Direct Share Windows එක Open කරන Logic එක
    private void shareThanuBoard() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, SHARE_MESSAGE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        Intent chooserIntent = Intent.createChooser(shareIntent, "Share ThanuBoard via");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }

    @Override public void onPress(int primaryCode) {}
    @Override public void onRelease(int primaryCode) {}
    @Override public void onText(CharSequence text) {}
    @Override public void swipeLeft() {}
    @Override public void swipeRight() {}
    @Override public void swipeDown() {}
    @Override public void swipeUp() {}
}
