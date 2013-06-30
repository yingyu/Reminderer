package com.frankandrobot.reminderer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.frankandrobot.reminderer.Helpers.MultiOsSupport;
import com.frankandrobot.reminderer.parser.ContextFreeGrammar;
import com.frankandrobot.reminderer.parser.Task;
import com.frankandrobot.reminderer.parser.datetime.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	Calendar todayCalendar = new GregorianCalendar();
	SimpleDateFormat sdf = new SimpleDateFormat("EEE");
	String today = sdf.format(new Date());
	todayCalendar.add(Calendar.DAY_OF_MONTH, 1);
	MultiOsSupport multiOs = MultiOsSupport.Factory.newInstance();
//	String tomorrow = multiOs.getDisplayName(todayCalendar,
//		Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
	// parseTester("hello"); // this should pass
	// parseTester("buy eggs"); // pass
	// parseTester("buy eggs " + today);
	// parseTester("buy eggs " + tomorrow);
	// parseTester("buy eggs Monday 9pm");
	// parseTester("buy milk June 2"); // pass
	// parseTester("buy eggs June 2 8pm"); // pass
	// parseTester("buy eggs June 2 10pm"); // pass
	// parseTester("buy eggs 630am July 1"); // pass
	// parseTester("milk at July 1 on 7:15pm"); // pass
	// parseTester("buy egss repeats daily"); // pass
	parseTester("buy milk next " + today);
	// parseTester("buy milk next " + tomorrow);
	// parseTester("buy milk at walmart");
    }

    private void stringTester(final String string) {
	DateTimeFormat.DateFormat form = new DateTimeFormat.DateFormat(this);
	String[] date = form.find(string);
	if (date == null)
	    Log.d("R", "date is null");
	else
	    Log.d("R", date[0] + " " + date[1]);
    }

    private void parseTester(final String string) {
	ContextFreeGrammar parser = new ContextFreeGrammar();
	parser.setAndroidContext(this);
	Task rslt = parser.parse(string);
	Log.d("R", "------\n" + string + "\n" + rslt);
    }
}