package com.The_Inevitables.NavUP.web.api.dto;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MyDateDeSerializeryyyyMMdd extends JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (p == null || p.getValueAsString() == null || p.getValueAsString().isEmpty()) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// 'T'HH:mm:ssz
		String theStringValueValue = p.getValueAsString();
		try {
			Date theDate = formatter.parse(theStringValueValue);
			Calendar theCalendar = Calendar.getInstance();
			theCalendar.setTime(theDate);
			return theCalendar;
		} catch (ParseException e) {
			throw new IOException("Custom Serializer Error: Unable to parse date with value being " + theStringValueValue);
		}
	}

}
