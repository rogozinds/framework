/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.components.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractTestUI;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.Calendar.TimeFormat;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;

@Theme("tests-calendar")
public class SetFirstVisibleHourOfDaySpecialCase extends AbstractTestUI {

    private Calendar calendar;

    @Override
    protected void setup(VaadinRequest request) {
        calendar = new Calendar();

        try {
            BasicEvent event = new BasicEvent("EVENT NAME 1", "EVENT TOOLTIP 1",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-05 16:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-06 00:00"));
            event.setStyleName("color1");
            calendar.addEvent(event);
        } catch (ParseException e1) { // Nothing to do
            e1.printStackTrace();
        }

        try {
            calendar.setStartDate(
                    new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-01"));
            calendar.setEndDate(
                    new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-30"));
        } catch (ParseException e) { // Nothing to do

        }

        calendar.setImmediate(true);
        calendar.setTimeFormat(TimeFormat.Format24H);
        calendar.setLocale(new Locale("en", "US"));

        calendar.setFirstVisibleHourOfDay(15);

        addComponent(calendar);
        calendar.setSizeFull();
        setSizeFull();
    }

    @Override
    protected String getTestDescription() {
        return "Calendar week and day views should work correctly when using setFirstVisibleHourOfDay() and "
                + "setting the end time of event to 00:00 of the following day";
    }

    @Override
    protected Integer getTicketNumber() {
        return 14737;
    }

}
