package mu.zz.axin.weathery.chart;

import android.annotation.SuppressLint;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mu.zz.axin.weathery.Util;


public class DiagramDateFormatter implements IAxisValueFormatter {

    private long referenceTimestamp;
    private DateFormat mDataFormat;
    private Date mDate;

    @SuppressLint("SimpleDateFormat")
    DiagramDateFormatter(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.mDataFormat = new SimpleDateFormat("dd.MM");
        this.mDate = new Date();
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long originalTimestamp = (long) value;
        return getDT(originalTimestamp);
    }

    private String getDT(long timestamp) {
        try {
            Date date = Util.getDateFromTS(String.valueOf(timestamp));
            return mDataFormat.format(date);
        } catch (Exception ex) {
            return "xx";
        }
    }
}
