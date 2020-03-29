package com.alina.mylibrary;

import java.text.SimpleDateFormat;

public interface MyLibraryConstants {
    final String REQUEST_DATE_FORMAT = "dd/MM/yyyy";
    final String RESPONSE_DATE_FORMAT = "dd/MM/yyyy";
    final SimpleDateFormat formatter = new SimpleDateFormat(REQUEST_DATE_FORMAT);
    final SimpleDateFormat displayFormatter = new SimpleDateFormat(RESPONSE_DATE_FORMAT);
}
