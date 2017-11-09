package com.hanqian.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
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
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.hanqian.util.ColorLibray;


/**
 * 天气情况线图
 * @author clczp
 *
 */
public class WeatherLineChart  {
	private List<ChartData> list=null;		//图表数据集
	private String title=null;				//标题
	private String unit=null;				//单位
	
	
	public WeatherLineChart(List<ChartData> list, String title,String unit) {
		super();
		this.list = list;
		this.title = title;
		this.unit  = unit;
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
			for (ChartData obj:list) {
				dataset.addValue(obj.getValue().doubleValue(), obj.getRowKey(), obj.getColumnKey());				
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
		DecimalFormat df1 = new DecimalFormat("#.##"+unit);//格式化  保留两位小数	
			//标题字体
			chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
			//chart.setBackgroundPaint(new Color(241, 248, 251));		//背景色
			//chart.setBackgroundImageAlpha(1f);
			//标签字体
			chart.getLegend().setItemFont(font);
			//自定义标签字体
	        LegendTitle legendtitle = new LegendTitle(chart.getPlot()); 
	        legendtitle.setPosition(RectangleEdge.RIGHT); //布局位置
	        chart.addSubtitle(legendtitle); 
	        chart.getLegend().setVisible(false);//不显示原下边标签
			
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
			
			
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();// 数据点   
			renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0}{1}={2}",df1));//map 提示信息
//			if (null != list) {
//				for (int i = 0; i < list.size(); i++) {	
//					renderer.setSeriesToolTipGenerator(i,new StandardCategoryToolTipGenerator(list.get(i).getMapTitle(),
//							NumberFormat.getPercentInstance()));				
//				}				
//			}
			// series 点（即数据点）可见   
			//lineandshaperenderer.setBaseShapesVisible(true);  
			// 显示数据点的数据   
			//renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());   
			// 显示折线图点上的数据   
			//lineandshaperenderer.setBaseItemLabelsVisible(true); 

		
						
			//线的大小
			renderer.setStroke(new BasicStroke(2F));//setSeriesStroke			
			//lineandshaperenderer.setSeriesStroke(1,new BasicStroke(6F) );//各线的大小
			
			
			//点的大小
			renderer.setShapesVisible(true);
			//renderer.setShape(new   java.awt.Rectangle(-4,-4,8,8)); //距形
		    renderer.setShape(new   java.awt.geom.Ellipse2D.Double(-3,   -3,   6,   6));//圆形
			renderer.setFillPaint(java.awt.Color.BLUE); 			
		    
		    
		 	
		
		

		iplot.setNoDataMessageFont(font); // 无数据字体
		iplot.setNoDataMessage("暂无数据!");// 无数据提示

	}

	/**
	 * 设置数据区颜色
	 * @param chart
	 * @param list
	 */
	private void setDataColor(JFreeChart chart) {
		Paint paint = null;
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		HashMap<String,Color> m = ColorLibray.getColorMap();
		LineAndShapeRenderer  ren= (LineAndShapeRenderer) plot.getRenderer();
		
		for (int i = 0; i < list.size(); i++) {
			paint = m.get(""+i);
			if (null == paint) {
				paint=new Color((int) (Math.random() * 255), 
					(int) (Math.random() * 255), (int) (Math.random() * 255));
			}
			ren.setSeriesPaint(i,paint);
		}
					
	}	
}
