package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class DobAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

	@Autowired
	private AnonymisationAsyncManager anonymisationAsyncMgr;

	@Override
	public List<SourceValue> convert(List<SourceValue> source) {

		Function<SourceValue, Object> dob = d -> {
			Object n = d.getValue();
			String finalDob = null;
			String input = null;
			if (n == null) {
				finalDob = "2001-01-01";
			} else {
				try {
					input = modifyDateLayout(n);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finalDob = input + "-01-01";
			}

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = (Date) formatter.parse(finalDob);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			
			return timeStampDate;
		};

		return this.anonymisationAsyncMgr.executeAsync(source, dob);

	}

	private static String modifyDateLayout(Object inputDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(inputDate.toString());

		return new SimpleDateFormat("yyyy").format(date);
	}
}
