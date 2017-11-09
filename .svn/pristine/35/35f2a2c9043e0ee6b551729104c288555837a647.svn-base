package com.hanqian.common;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

/**
 * 业务能耗柱状图
 * @author clczp
 *
 */
public class BusinessRecChart  {

	private List<ChartData> list;//数据源
	private String title;//标题
	private String unit;//单位
	private HashMap<Object,Object> series=new HashMap<Object,Object>();

	
	public BusinessRecChart(List<ChartData> list, String title,String unit) {
		this.list = list;
		this.title = title;
		this.unit = unit;
	}

	/**
	 * 图形
	 * @return
	 */
	public JFreeChart getChart() {
		JFreeChart chart = ChartFactory.createBarChart(title, // 标题
				null, // X轴标签
				null, // Y轴标签
				this.getCategoryDataset(), // 数据
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示下边 标签
				true, // 是否生成工具
				false);// 是否生成URL链接

		this.configFont(chart); // 设置字体(中文乱码问题)
		//this.setDataColor(chart); //设置数据区颜色

		return chart;
	}

	

	/**
	 * 柱形图数据
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		if (list != null) {
			ChartData obj = null;
			Comparable key = null;
			for (int i = 0; i < list.size(); i++) {
				obj = list.get(i);
				key = obj.getRowKey();
				dataset.addValue(obj.getValue(),key,obj.getColumnKey());
				if (series.get(key) == null) {
					series.put(key, true);
				}
			}
		}
		
		return dataset;
	}
	
	
	
	/**
	 * 字体样式
	 * 
	 * @param chart
	 */
	private void configFont(JFreeChart chart) {
		Font font = new Font("宋体", Font.PLAIN, 12);
		Plot iplot=chart.getPlot();
		
		//标题字体
		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
		DecimalFormat df1 = new DecimalFormat("#.###");//格式化  保留两位小数	
		
		//标签字体
		chart.getLegend().setItemFont(font);
		//自定义标签字体
        LegendTitle legendtitle = new LegendTitle(chart.getPlot()); 
        legendtitle.setPosition(RectangleEdge.RIGHT); //布局位置
        chart.addSubtitle(legendtitle); 
        chart.getLegend().setVisible(false);//不显示原下边标签
        
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();   
	 	numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());//设置y轴显示数据类型
	 	numberAxis.setNumberFormatOverride(df1);
	 	numberAxis.setTickLabelFont(font);//字体
	 	
        //X轴标签字体
		CategoryAxis domainAxis =plot.getDomainAxis();
		domainAxis.setTickLabelFont(font);
	 	// 用于显示X轴刻度   
		domainAxis.setTickMarksVisible(true);  
		//设置X轴45度   
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		//设置每一组柱状体之间的间隔
		BarRenderer render = (BarRenderer) plot.getRenderer();
		render.setItemMargin(0.0);//各柱形间距
		render.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}{0}:{2}",NumberFormat.getNumberInstance()));//map 提示信息
//		if (null != list) {
//			for (int i = 0; i < list.size(); i++) {
//				render.setSeriesToolTipGenerator(i,
//						new StandardCategoryToolTipGenerator(list.get(i)
//								.getMapTitle(), NumberFormat
//								.getPercentInstance()));
//			}
//
//		}
		iplot = plot;
		
		
		

		iplot.setNoDataMessageFont(font); // 无数据字体
		iplot.setNoDataMessage("暂无数据!");// 无数据提示

	}

	/**
	 * 设置数据区颜色
	 * 
	 * @param chart
	 * @param list
	 */
	private void setDataColor(JFreeChart chart) {
//		CategoryPlot plot = (CategoryPlot) chart.getPlot();
//		BarRenderer renderer = (BarRenderer) plot.getRenderer();
//		if (null != list) {
//			HashMap<String, Color> colormap = ColorLibray.getColorMap();
//
//			for (int i = 0; i < series.size(); i++) {
//				Color color = colormap.get(i + "");
//				if (null == color) {
//					color = new Color((int) (Math.random() * 255), (int) (Math
//							.random() * 255), (int) (Math.random() * 255));
//				}
//
//				renderer.setSeriesPaint(i, color);
//			}
//		}
	}

	
}
