package com.hanqian.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.hanqian.util.ColorLibray;


/**
 * 业务能耗折线图
 * @author clczp
 *
 */
public class BusinessLineChart  {
	private List<ChartData> list=null;		//图表数据集
	private String title=null;				//标题
	private String unit=null;				//单位
	private boolean lastBlack=false;
	private HashMap<Object,Object> series=new HashMap<Object, Object>();//线条数
	
	//记录排序
	class ComparatorCharList implements Comparator {

		public int compare(Object obja, Object objb) {
			ChartData obj1= (ChartData)obja;
			ChartData obj2= (ChartData)objb;
			//比较
			int flag =0;
			if (obj1 !=null && obj2 !=null) {
				flag =((String)obj1.getColumnKey()).compareTo((String)obj2.getColumnKey());
			}
			
			return flag;
		}
	}
	
	
	
	public BusinessLineChart(List<ChartData> list, String title,String unit,
			boolean lastBlack) {
		super();
		this.list = list;
		this.title = title;
		this.unit  = unit;
		this.lastBlack = lastBlack;
	}




	/**
	 * 图形
	 * @return
	 */
	public JFreeChart getChart() {
		JFreeChart chart = ChartFactory.createLineChart(title, 
				null, 
				null, 
				this.getLineDataset(), 
				PlotOrientation.VERTICAL, 
				true, // 是否显示下边 标签
				true, 
				false);		

		this.configFont(chart); // 设置字体(中文乱码问题)
		this.setDataColor(chart); //设置数据区颜色

		return chart;
	}

	
	
	/**
	 * 折线图数据
	 * @return
	 */
	private DefaultCategoryDataset getLineDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (null != list) {
			//重序
			Collections.sort(list,new ComparatorCharList());
			Comparable key = null;
			for (ChartData obj:list) {
				key =  obj.getRowKey();
				dataset.addValue(obj.getValue().doubleValue(), key, obj.getColumnKey());				
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
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		DecimalFormat df1 = new DecimalFormat("#.###");//格式化  保留两位小数	
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());// 设置y轴显示数据类型
		numberAxis.setNumberFormatOverride(df1);
		numberAxis.setTickLabelFont(font);//字体
			
		//标题字体
		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
		// chart.setBackgroundPaint(new Color(241, 248, 251)); //背景色
		// chart.setBackgroundImageAlpha(1f);
		// 标签字体
		chart.getLegend().setItemFont(font);
		// 自定义标签字体
		LegendTitle legendtitle = new LegendTitle(chart.getPlot());
		legendtitle.setPosition(RectangleEdge.RIGHT); // 布局位置
		chart.addSubtitle(legendtitle);
		chart.getLegend().setVisible(false);// 不显示原下边标签

	

		// X轴标签字体
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(font);
		// 用于显示X轴刻度
		domainAxis.setTickMarksVisible(true);
		// 设置X轴45度
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();// 数据点
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}{0}={2}",df1));//map 提示信息
//		if (null != list) {
//			for (int i = 0; i < list.size(); i++) {
//				renderer.setSeriesToolTipGenerator(i,
//						new StandardCategoryToolTipGenerator(list.get(i)
//								.getMapTitle(), NumberFormat
//								.getPercentInstance()));
//			}
//
//		}
		// series 点（即数据点）可见
		// lineandshaperenderer.setBaseShapesVisible(true);
		// 显示数据点的数据
		// renderer.setBaseItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// 显示折线图点上的数据
		// lineandshaperenderer.setBaseItemLabelsVisible(true);

		// 线的大小
		renderer.setStroke(new BasicStroke(2F));// setSeriesStroke
		// lineandshaperenderer.setSeriesStroke(1,new BasicStroke(6F) );//各线的大小

		// 点的大小
		renderer.setShapesVisible(true);
		// renderer.setShape(new java.awt.Rectangle(-4,-4,8,8)); //距形
		renderer.setShape(new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6));// 圆形
		renderer.setFillPaint(java.awt.Color.BLUE);
		
		

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
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		if (null != list) {
			HashMap<String, Color> colormap = ColorLibray.getColorMap();

			for (int i = 0; i < series.size(); i++) {
				Color color = colormap.get(i + "");
				if (null == color) {
					color = new Color((int) (Math.random() * 255), (int) (Math
							.random() * 255), (int) (Math.random() * 255));
				}
				if (lastBlack && (i + 1) == series.size()) {
					// 基线
					color = new Color(0, 0, 0);
				}

				renderer.setSeriesPaint(i, color);
			}
		}
	}
	
}
