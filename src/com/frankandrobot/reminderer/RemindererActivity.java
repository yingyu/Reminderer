package com.frankandrobot.reminderer;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.frankandrobot.reminderer.Parser.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class RemindererActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	// stringTester("June 2, 2003<--this should work");
	// stringTester("6/2<--this should work");
	// stringTester("Jun 2, 2003<---this should work");
	// stringTester("Jun 2<---this should work");
	// stringTester("hello June 2<---this should fail");
	 parseTester("hello"); // this should pass
	 parseTester("buy eggs"); // pass
	 parseTester("buy milk June 2"); //pass
	 parseTester("buy eggs June 2 8pm"); // pass
	 parseTester("buy eggs June 2 10pm"); // pass
	 parseTester("buy eggs 630am July 1"); //pass
	 parseTester("milk at July 1 on 7:15pm"); // pass
	 parseTester("buy egss repeats daily"); // pass
	 parseTester("buy milk next Monday");
    }

    private void stringTester(final String string) {
	MyDateTimeFormat.DateFormat form = new MyDateTimeFormat.DateFormat(this);
	String[] date = form.find(string);
	if (date == null)
	    Log.d("R", "date is null");
	else
	    Log.d("R", date[0] + " " + date[1]);
    }

    private void parseTester(final String string) {
	MetaGrammarParser parser = new MetaGrammarParser();
	parser.setAndroidContext(this);
	MetaGrammarParser.Task rslt = parser.parse(string);
	Log.d("R", "------\n" + string + "\n" + rslt);
    }
}