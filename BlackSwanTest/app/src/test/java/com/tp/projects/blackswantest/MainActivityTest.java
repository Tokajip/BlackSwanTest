package com.tp.projects.blackswantest;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;

/**
 * Created by Tokaji Peter on 21/07/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MovieActivity> {

    public MainActivityTest() {
        super(MovieActivity.class);
    }

    MovieActivity activity;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }


    public void testApp() throws Exception {
        assertNull(activity);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
