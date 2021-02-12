package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.util.EventLogTags;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.gms.common.internal.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Typeface.BOLD;

public class ReportesGraficas extends AppCompatActivity {
    AnyChartView anyChartView;
    PieChart piechart;
    Typeface font;
    BarChart barChart;

    String[] dias = {"Enero", "Febrero", "Marzo", "Abril", "Mayo"};
    int[] sueldos = {900, 1000, 400, 567, 787};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_graficas);


        anyChartView = findViewById(R.id.anychart);
        piechart = findViewById(R.id.graficopastel1);
        barChart = findViewById(R.id.barchart);
        // crearGraficoPastel();
        //  font = Typeface.createFromAsset(getAssets(), "DroidSans.ttf");
        dibujaPieEntries();
        setupPieChart();
        establecerGraficodeBarras();

    }

    private void establecerGraficodeBarras() {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2012, 390));
        barEntries.add(new BarEntry(2013, 90));
        barEntries.add(new BarEntry(2014, 290));
        barEntries.add(new BarEntry(2015, 990));
        barEntries.add(new BarEntry(2016, 710));
        barEntries.add(new BarEntry(2017, 800));
        barEntries.add(new BarEntry(2018, 50));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Nro Empleados");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);


        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("BAR CHART EXAMPLE");
        barChart.animateY(2000);


    }


    private void setupPieChart() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();


        for (int i = 0; i < dias.length; i++) {

            dataEntries.add(new ValueDataEntry(dias[i], sueldos[i]));


        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);

    }


    private void crearGraficoPastel() {

        Description description = new Description();
        description.setText("REPORTE EMPLEADOS");
        description.setPosition(11, 11);
        description.setTextSize(11);
        piechart.setDescription(description);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(20, "Phyton"));
        pieEntries.add(new PieEntry(30, "C++"));
        pieEntries.add(new PieEntry(60, "JAVA"));


        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Employees");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);
        pieDataSet.setDrawIcons(true);
        PieData pieData = new PieData(pieDataSet);
        piechart.setData(pieData);


    }


    public void chartDetails1(PieChart mChart, Typeface tf) {
        mChart.animateXY(1400, 1400);
        mChart.getDescription().setEnabled(false);
        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText("");
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(tf);
        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setWordWrapEnabled(true);
        l.setDrawInside(false);
        mChart.setTouchEnabled(true);
        mChart.setDrawEntryLabels(false);
        mChart.setExtraOffsets(25.f, 0.f, 25.f, 0.f);

        mChart.setUsePercentValues(true);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setTransparentCircleRadius(61f);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setHighlightPerTapEnabled(true);
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);
        l.setEnabled(true);
        mChart.highlightValues(null);
        mChart.setUsePercentValues(true);
        /*new*/
        mChart.setHoleRadius(30f);
        mChart.setDrawCenterText(false);
        mChart.setTransparentCircleRadius(35f);

        mChart.getDescription().setEnabled(false);
        mChart.setRotationEnabled(false);

        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(9f);

    }

    public ArrayList<Integer> colorsList() {
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        return colors;

    }

    public PieData pieDatasetSlice(PieDataSet dataSet) {
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(100f); /** When valuePosition is OutsideSlice, indicates offset as percentage out of the slice size */
        dataSet.setValueLinePart1Length(0.6f); /** When valuePosition is OutsideSlice, indicates length of first half of the line */
        dataSet.setValueLinePart2Length(0.6f); /** When valuePosition is OutsideSlice, indicates length of second half of the line */
        dataSet.setSliceSpace(0f);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(5f);
        data.setValueTextColor(Color.BLACK);
        data.setValueFormatter(new PercentFormatter());
        return data;
    }

     /*chartDetails1(piechart, font);
         Description description = new Description();
         description.setText("REPORTE EMPLEADOS");

         piechart.setDescription(description);

         ArrayList<PieEntry> pieEntries = new ArrayList<>();

         pieEntries.add(new PieEntry(2, "hombres", "el pepe"));
         pieEntries.add(new PieEntry(3, "varones", 5));
         pieEntries.add(new PieEntry(6, "mujeres maric", 7));









         PieDataSet dataSet = new PieDataSet(pieEntries, null);
         ArrayList<Integer> colors = Constants.colorsList();
         dataSet.setColors(colors);
         PieData data = Constants.pieDatasetSlice(dataSet);




     }*/

    public void dibujaPieEntries() {
        Description description = new Description();
        description.setText("REPORTE EMPLEADOS");
        description.setTextSize(11);
        piechart.setDescription(description);


        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(20, "Phyton"));
        pieEntries.add(new PieEntry(30, "C++"));
        pieEntries.add(new PieEntry(60, "JAVA"));


        PieDataSet dataSet = new PieDataSet(pieEntries, "LEYENDA");
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
// Outside values
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(50f); /** estaba en 100 When valuePosition is OutsideSlice, indicates offset as percentage out of the slice size */
        dataSet.setValueLinePart1Length(0.4f); /** estaba en 0.6f When valuePosition is OutsideSlice, indicates length of first half of the line */
        dataSet.setValueLinePart2Length(0.4f); /** When valuePosition is OutsideSlice, indicates length of second half of the line */
        //   setExtraOffsets(0.f, 5.f, 0.f, 5.f); // Ofsets of the view chart to prevent outside values being cropped /** Sets extra offsets (around the chart view) to be appended to the auto-calculated offsets.*/

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);// estaba en 10f
        data.setValueTextColor(Color.BLACK);
        piechart.setData(data);
    }


}