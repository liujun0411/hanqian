package com.hanqian.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 区域能耗折线图
 * @author clczp
 *
 */
public class RegionalLineChart  {
	private List<ChartData> list=null;		//数据集
	private String title=null;				//标题
	private String unit=null;				//单位
	private boolean lastBlack=false;
	private int lastAt = 0;
	private HashMap<Object,Object> series=new HashMap<Object, Object>();//线条数
	private Map<String, Map<String,ChartData>> mn = new HashMap<String, Map<String,ChartData>>();
	private List<String> nlist = new ArrayList<String>();
	private List<String> tlist = new ArrayList<String>();
	
	
	
	
	
	public RegionalLineChart(List<ChartData> list, String title,String unit,
			boolean lastBlack) {
		super();
		this.list = list;
		this.title = title;
		this.unit  = unit;
		this.lastBlack = lastBlack;
		
		if (list != null ) {
			//重序
			Collections.sort(list,new ComparatorCharList());			
			
			//重新封装
			Map<String, Integer> mt = new HashMap<String, Integer>();
			String key = null;
			String time = null;
			for (int i = 0; i < list.size(); i++) {
				key = list.get(i).getRowKey()+"";
				time = list.get(i).getColumnKey()+"";
				if (mn.get(key) == null) {
					mn.put(key,new HashMap<String, ChartData>());
					nlist.add(key);
				}				
				mn.get(key).put(time,list.get(i));
				
				if (mt.get(time) == null) {
					mt.put(time, 1);
					tlist.add(time);
				}			
			}
		}
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

		this.configFont(chart); // 设置字体
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
			Comparable lastKey = "上海市";
//			if (lastBlack) {				
//				for(ChartData obj:list){
//					if(obj.getRowKey().equals("上海")){
//						lastKey =  obj.getRowKey();	
//						break;
//					}
//				}				
//			}
			
			Comparable key = null;
			for (ChartData obj:list) {
				key =  obj.getRowKey();
				dataset.addValue(obj.getValue().doubleValue(), key, obj.getColumnKey());				
				if (series.get(key) == null) {
					series.put(key, true);
					if (lastKey != null) {
						if(!key.equals(lastKey)){
							lastAt ++;
						}else{
							lastKey = null;
						}
					}
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
		plot.setRangeZeroBaselineVisible(false);//为零的数据不显示
		
		DecimalFormat df1 = new DecimalFormat("#.###"+unit);//格式化  保留两位小数
		
		// 标题字体
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

		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());// 设置y轴显示数据类型
		numberAxis.setNumberFormatOverride(df1);
		numberAxis.setTickLabelFont(font);// 字体

		// X轴标签字体
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(font);
		// 用于显示X轴刻度
		domainAxis.setTickMarksVisible(true);
		// 设置X轴45度
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		
		
		StandardCategoryToolTipGenerator[][] toolTips = createToolTip(); // 自定义点提示
		LineRender renderer = new LineRender(toolTips);
//		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
//				.getRenderer();// 数据点
//		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0} {1}={2}", df1));// map 提示信息
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
		 	
		plot.setRenderer(renderer);
		

		iplot.setNoDataMessageFont(font); // 无数据字体
		iplot.setNoDataMessage("暂无数据!");// 无数据提示

	}

	/**
	 * 提示信息
	 * @return
	 */
	public StandardCategoryToolTipGenerator[][] createToolTip(){
		StandardCategoryToolTipGenerator[][] toolTips = new StandardCategoryToolTipGenerator[nlist
				.size()][tlist.size()];
		
		ChartData obj = null;
		for (int i = 0; i < nlist.size(); i++) {			
			for (int j = 0; j < tlist.size(); j++) {
				obj = mn.get(nlist.get(i)).get(tlist.get(j));
				if (obj != null) {
					toolTips[i][j] = new StandardCategoryToolTipGenerator(obj.getMapTitle(),
							NumberFormat.getPercentInstance());
				}
				
			}
		}
		
		return toolTips;
	}
	
	
	/**
	 * 设置数据区颜色
	 * 
	 * @param chart
	 * @param list
	 */
	private void setDataColor(JFreeChart chart) {
		CategoryPlot plot = (CategoryPlot)chart.getPlot();			
		LineAndShapeRenderer  renderer = (LineAndShapeRenderer) plot.getRenderer();
		if(null !=list){
			HashMap<String, Color> colormap = ColorLibray.getColorMap();
			
			
			
			for (int i =0; i< mn.size(); i++) {				
				Color color = colormap.get((i+1)+"");
				if (null == color) {
					color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
				}
				
				
				if (lastBlack && i == lastAt){
					//基线
					color = new Color(0,0,0);
				}
				
				renderer.setSeriesPaint(i, color);		
			}
			
			if (!lastBlack) {
				//气温线条颜色			
				renderer.setSeriesPaint(mn.size()-1, colormap.get("0"));
			}
		}
	}

}
