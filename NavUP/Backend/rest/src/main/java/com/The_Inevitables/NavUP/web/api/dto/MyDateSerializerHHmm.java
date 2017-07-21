package com.The_Inevitables.NavUP.web.api.dto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MyDateSerializerHHmm extends JsonSerializer<Calendar> {

	@Override
	public void serialize(Calendar value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		if (value == null) {
			return;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");// 'T'HH:mm:ssz
		gen.writeString(formatter.format(value.getTime()));
	}

}
