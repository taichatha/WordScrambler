package tchatha.wordscrambler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


public class MainActivity extends Activity {
    EditText wordPlace;
    TextView sWord;
    Button sButton;
    String word;
    String scrambledWord;
    int used[];
    Random randomGenerator = new Random();
    Set<Integer> generated;
    RelativeLayout mainLayout;

    // Need to add keyboard hiding. no spaces. that's it.xvir
    @Override
    protected void onCreate(Bundle savedInstanceState) {


// Get your layout set up, this is just an example
        mainLayout = (RelativeLayout)findViewById(R.id.relLayout);

// Then just use the following:

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordPlace = (EditText) findViewById(R.id.editText2);
        wordPlace.setImeActionLabel("Scramble", KeyEvent.KEYCODE_ENTER);
        wordPlace.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    onClick(v);
                    return true;

                }
                return false;
            }
        });
        sWord = (TextView) findViewById(R.id.textView6);
        sButton = (Button) findViewById(R.id.sButton);


    }



    public void onClick(View v) {
        word = wordPlace.getText().toString();
        scrambledWord = "";
        generated = new LinkedHashSet<Integer>();
        used = new int[word.length()];
        while(generated.size() < word.length()){
            Integer next = randomGenerator.nextInt(word.length());
            generated.add(next);
            System.out.println(next);
        }
        System.out.println(generated.toString());
        for(Integer x : generated) {
            if(x < word.length()) {
                scrambledWord += word.charAt(x);
                System.out.println(scrambledWord);
            }


        }

        sWord.setText(scrambledWord);
//        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        // it was the 1st button
    }

}
