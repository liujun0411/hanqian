package com.hanqian.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.hanqian.util.ColorLibray;
import com.hanqian.util.DrawType;

/**
 * 区域 图形
 *  * @author czpsky
 * 
 */
public class QuYuChart  {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(QuYuChart.class);
	private List datalist = null;
	private List baseLineList=null;
	private HashMap series=new HashMap();
	
	public QuYuChart(List datalist) {
		this.datalist = datalist;
	}
	
	public QuYuChart(List datalist,List baseLineList) {
		this.datalist = datalist;
		this.baseLineList = baseLineList;
	}

	/**
	 * 图形
	 * @return
	 */
	public JFreeChart getChart(DrawType drawType) {
		JFreeChart chart = ChartFactory(drawType);

		this.configFont(chart,drawType); // 设置字体(中文乱码问题)
		this.setDataColor(chart,drawType); //设置数据区颜色

		return chart;
	}

	/**
	 * 图形工厂
	 * 
	 * @param drawtype
	 * @return
	 */
	private JFreeChart ChartFactory(DrawType drawType) {
		JFreeChart chart = null;
		if (DrawType.zhuZhuang == drawType) {
			chart = ChartFactory.createBarChart("柱状数据区", // 标题
					null, // X轴标签
					null, // Y轴标签
					this.getCategoryDataset(), // 数据
					PlotOrientation.VERTICAL, // 图表方向：水平、垂直
					true, // 是否显示下边 标签
					true, // 是否生成工具
					false);// 是否生成URL链接
		} else if (DrawType.zheXian == drawType) {
			chart = ChartFactory.createLineChart("折线数据区", 
					null, 
					null, 
					this.getLineDataset(), 
					PlotOrientation.VERTICAL, 
					true, // 是否显示下边 标签
					true, 
					false);			
		} else if (DrawType.bingZhuang == drawType) {
//			chart = ChartFactory.createPieChart(null, // 标题
//					this.getPieDataset(), // 数据
//					false, // 是否显示下边 标签
//					false, // 是否生成工具
//					false);// 是否生成URL链接
//			chart =ChartFactory.createMultiplePieChart(null,this.getPieDataset(),TableOrder.BY_COLUMN, false, false, false);
			
		}

		return chart;
	}

	/**
	 * 柱形图数据
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		if (datalist != null) {
			for (int i = 0; i < datalist.size(); i++) {
				HashMap map = (HashMap) datalist.get(i);
				//avgpower  convert
				Double value =0D;
				try {
					value = Double.parseDouble(map.get("value").toString());					
				} catch (Exception e) {
					logg.error("QuYuChart-->getCategoryDataset", e);
				}				
				dataset.addValue(value, map.get("name").toString(),map.get("type").toString());
			}
		}
		
		return dataset;
	}
	
	/**
	 * 折线图数据
	 * @return
	 */
	private DefaultCategoryDataset getLineDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (baseLineList != null) {
			for (int i = 0; i < baseLineList.size(); i++) {
				HashMap map = (HashMap) baseLineList.get(i);
				//avgpower  convert
				Double value =0D;
				try {
					value = Double.parseDouble(map.get("value").toString());					
				} catch (Exception e) {logg.error("QuYuChart-->getLineDataset", e);}				
				dataset.addValue(value, map.get("name").toString(),map.get("type").toString());
				Object obj = series.get(map.get("name").toString());
				if (null == obj) {
					series.put(map.get("name").toString(), true);
				}
			}
		}
		if (datalist != null) {
			for (int i = 0; i < datalist.size(); i++) {
				HashMap map = (HashMap) datalist.get(i);
				//avgpower  convert
				Double value =0D;
				try {
					value = Double.parseDouble(map.get("value").toString());					
				} catch (Exception e) {logg.error("QuYuChart-->getLineDataset", e);}				
				dataset.addValue(value, map.get("name").toString(),map.get("type").toString());
				Object obj = series.get(map.get("name").toString());
				if (null == obj) {
					series.put(map.get("name").toString(), true);
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
	private void configFont(JFreeChart chart,DrawType drawtype) {
		Font font = new Font("宋体", Font.PLAIN, 12);
		Plot iplot=chart.getPlot();
		
		
		if (DrawType.zhuZhuang.equals(drawtype)) {
			
			//标题字体
			chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
			
			
			//标签字体
			chart.getLegend().setItemFont(font);
			//自定义标签字体
	        LegendTitle legendtitle = new LegendTitle(chart.getPlot()); 
	        legendtitle.setPosition(RectangleEdge.RIGHT); //布局位置
	        chart.addSubtitle(legendtitle); 
	        chart.getLegend().setVisible(false);//不显示原下边标签
	        
	        CategoryPlot plot = (CategoryPlot)chart.getPlot();
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
			iplot = plot;
		}else if (DrawType.zheXian.equals(drawtype)) {
			CategoryPlot plot = (CategoryPlot)chart.getPlot();
			
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
			
			//X轴标签字体
			CategoryAxis domainAxis =plot.getDomainAxis();
			domainAxis.setTickLabelFont(font);
		 	// 用于显示X轴刻度   
			domainAxis.setTickMarksVisible(true);  
			//设置X轴45度   
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();// 数据点   
			renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0} {1}={2}",NumberFormat.getNumberInstance()));//map 提示信息
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
		    
		    
		 	
		}else if (DrawType.bingZhuang.equals(drawtype)) {
			MultiplePiePlot multiplepieplot = (MultiplePiePlot)chart.getPlot();
			PiePlot pieplot = (PiePlot)multiplepieplot.getPieChart().getPlot();
			pieplot.setMaximumLabelWidth(0.15D);
					
			pieplot.setBackgroundPaint(new Color(255, 255, 255));//背景颜色设置			
			pieplot.setOutlineVisible(false);//边框不显示			
			pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}{1}"));	//显示的信息 {0} name  {1} value  {2}百份比				
			pieplot.setLabelBackgroundPaint(new Color(255, 255, 255));//文字背影颜色
			pieplot.setSimpleLabels(false);	//扇区文字位置 true 内  false 外	
			
			// 饼图各扇区的字体	        
	        pieplot.setLabelFont(font);	//字体		
	        pieplot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);	//线条
	        pieplot.setLabelOutlinePaint(new Color(255, 255, 255)); //边框
	        pieplot.setLabelGap(0.15D);
	        
	       
	       
			iplot = pieplot;
		}
		
		

		iplot.setNoDataMessageFont(font); // 无数据字体
		iplot.setNoDataMessage("暂无数据!");// 无数据提示

	}

	/**
	 * 设置数据区颜色
	 * 
	 * @param chart
	 * @param list
	 */
	private void setDataColor(JFreeChart chart,DrawType drawtype) {
		if (DrawType.zhuZhuang.equals(drawtype)) {
			
		}else if (DrawType.zheXian.equals(drawtype)) {
			CategoryPlot plot = (CategoryPlot)chart.getPlot();
			
			
			LineAndShapeRenderer  renderer = (LineAndShapeRenderer) plot.getRenderer();
			// 线的颜色
			HashMap<String, Color> colormap = ColorLibray.getColorMap();
			for (int i = 0; i < series.size(); i++) {
				Color color = colormap.get((i) + "");
				if (null == color) {
					color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
				}
				
				if (null !=baseLineList && i==0) {
					//基线
					renderer.setSeriesPaint(i, new Color(0,0,0));
				}else{
					//非基线
					renderer.setSeriesPaint(i, color);
				}
				
				//renderer.getSeriesShape(i);
			}
			
			
		}else if (DrawType.bingZhuang.equals(drawtype)) {
			PiePlot pieplot = (PiePlot)((MultiplePiePlot)chart.getPlot()).getPieChart().getPlot();
			if(datalist != null){
				HashMap map = null;
				Color color = null;
				for (int i = 0; i < datalist.size(); i++) {
					map = (HashMap)datalist.get(i);
					if(getColor().get(i)==null){
						color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
					}else{
						color = this.getColor().get(i);
					}
					pieplot.setSectionPaint(map.get("name").toString(),color);
				}
			}
		}
	}

	/**
	 * 颜色库
	 * 
	 * @return
	 */
	private HashMap<String, Color> getColor() {
		HashMap<String, Color> map = new HashMap<String, Color>();
		map.put("1", new Color(121, 177, 165));
		map.put("2", new Color(239, 221, 172));
		map.put("3", new Color(139, 179, 212));
		map.put("4", new Color(222, 127, 59));
		map.put("5", new Color(102, 124, 180));
		map.put("6", new Color(166, 140, 135));
		map.put("7", new Color(172, 181, 123));
		map.put("8", new Color(214, 227, 103));
		map.put("9", new Color(220, 181, 89));
		map.put("10", new Color(221, 131, 161));
		map.put("11", new Color(249, 196, 123));
		return map;
	}
	
}
