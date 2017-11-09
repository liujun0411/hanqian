package com.hanqian.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
 * 楼宇能耗折线图
 * @author JKJ
 *
 */
public class ElectLineChart  {
	private List<ChartData> list=null;		//图表数据集
	private String title=null;				//标题
	private String unit=null;				//单位
	private boolean lastBlack=false;
	private int lastAt = 0;
	private String flag; //标识电流图类型
	private Integer maxVal;
	private Map<String, Map<String,ChartData>> mn = new HashMap<String, Map<String,ChartData>>();
	private List<String> nlist = new ArrayList<String>();
	private List<String> tlist = new ArrayList<String>();
	
	private HashMap<Object,Object> series=new HashMap<Object, Object>();//线条数
	public ElectLineChart(List<ChartData> list, String title,String unit,
			boolean lastBlack,String flag,Integer maxVal) {
		super();
		this.list = list;
		this.title = title;
		this.unit  = unit;
		this.lastBlack = lastBlack;
		this.flag=flag;
		this.maxVal=maxVal;
		if (list != null ) {
			//重序
			//Collections.sort(list,new ComparatorCharList());			
			
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
//				if (mt.get(time) == null) {
					mt.put(time, 1);
					tlist.add(time);
//				}	
				if(key!=null){
					if(key.indexOf("--")>0){
						//key=key.substring(key.indexOf("--")+1,1);
					}
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

		this.configFont(chart); // 设置字体(中文乱码问题)
		if("singel".equals(flag)){
		   this.setDataColor(chart); //设置数据区颜色
		}
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
//					if(lastKey != obj.getRowKey()){
//						lastKey =  obj.getRowKey();						
//					}
//				}				
//			}
			Comparable key = null;
				for (ChartData obj:list) {
					key =  obj.getRowKey();
						dataset.addValue(obj.getValue().doubleValue(), key, obj.getColumnKey());
						if (series.get(key) == null) {
							series.put(key, key);					
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
		
		DecimalFormat df1 = new DecimalFormat("#.###"+unit);//格式化  保留两位小数	
			//标题字体
			chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
			//chart.setBackgroundPaint(new Color(238, 238, 255));		//背景色
			chart.setBackgroundPaint(Color.white);   //设定背景色为白色
			//chart.setBackgroundImageAlpha(1f);
			//标签字体
			chart.getLegend().setItemFont(font);
			//自定义标签字体
	        LegendTitle legendtitle = new LegendTitle(chart.getPlot()); 
	        //chart.getLegend().setMargin(0, 400,0,0);
	        /**
	         * 控制标签位置
	         */
	        legendtitle.setPadding(-3, 400,0,0);
	       // legendtitle.setPosition(RectangleEdge.BOTTOM); //布局位置
	        chart.addSubtitle(legendtitle); 
	        chart.getLegend().setVisible(false);//不显示原下边标签
	        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis(); 
	        numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());//设置y轴显示数据类型
		 	numberAxis.setNumberFormatOverride(df1);
		 	numberAxis.setTickLabelFont(font);//字体
		 	numberAxis.setAutoRangeIncludesZero(true);
		 //	numberAxis.setRangeWithMargins(10, 10)  ; //             setRangeGridlinesVisible(true);
		 	CategoryPlot categoryplot = chart.getCategoryPlot(); 
		 	//综合
		 	if("total".equals(flag)){
		 		if(this.maxVal!=null && this.maxVal>0){
		 			plot.getRangeAxis().setRange(0, this.maxVal*3);	
		 		}else{
		 		   plot.getRangeAxis().setRange(0, 1800*3);	
		 		}
		 		BufferedImage bi = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bi.createGraphics();
				g.setBackground(Color.white);
				g.setColor(Color.red);
				g.fillRect(0,0,600,40);
				
				g.setColor(Color.orange);
				
				g.fillRect(0,40,600,80);
				
				g.setColor(new Color(15,240,150));
//				g.setColor(Color.green);
				g.fillRect(0,80,600,400);
				bi.flush();
//				g.setColor(Color.white);
//			 	GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.red);//从上到下渐变的颜色
//			    GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.white, 20.0F, 100.0F, Color.orange);//从上到下渐变的颜色
//			 	categoryplot.setBackgroundPaint(g.getPaint());
				categoryplot.setBackgroundImage(bi);
		 	}else if("singel".equals(flag)){
                   /**
                    * 单项
                    * 设置三相电流背景色
                    */
		 		categoryplot.setBackgroundPaint(new Color(233,242,242));
		 		//categoryplot.setBackgroundPaint(Color.BLACK);
		 	}
				 	/**
				 	 * 新加的
				 	 */
		 	CategoryPlot categoryplots = (CategoryPlot) chart.getPlot();
	        // x轴 // 分类轴网格是否可见
	        categoryplots.setDomainGridlinesVisible(false);
	        // y轴 //数据轴网格是否可见
	        categoryplots.setRangeGridlinesVisible(true);
	        categoryplot.setRangeGridlinePaint(new Color(44,102,102));
	       // categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩
	        categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩
	        categoryplot.setBackgroundPaint(Color.lightGray);
	        
	        
			//X轴标签字体
			CategoryAxis domainAxis =plot.getDomainAxis();
			domainAxis.setTickLabelFont(font);
		 	// 用于显示X轴刻度   
			domainAxis.setTickMarksVisible(true); 
			//设置X轴45度   
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			StandardCategoryToolTipGenerator[][] toolTips = createToolTip(); // 自定义点提示
			LineRender renderer = new LineRender(toolTips);
			//线的大小
			renderer.setStroke(new BasicStroke(1F,
					BasicStroke.CAP_ROUND, // 端点风格  
	                BasicStroke.JOIN_ROUND // 折点风格
	         
			));//setSeriesStroke	
			//点的大小
			renderer.setShapesVisible(false);
			//renderer.setShape(new   java.awt.Rectangle(-4,-4,8,8)); //距形
		    renderer.setShape(new   java.awt.geom.Ellipse2D.Double(-3,   -3,   0,   0));//圆形
			renderer.setFillPaint(Color.BLUE); 			
			renderer.setSeriesPaint(0, Color.BLUE);
			renderer.setSeriesPaint(0, Color.blue);
			plot.setRenderer(renderer);
		iplot.setNoDataMessageFont(font); // 无数据字体
		iplot.setNoDataMessage("暂无数据!");// 无数据提示

	}
	
	/**
	 * 字体样式(综合样式)
	 * 
	 * @param chart
	 */
	private void configFontZH(JFreeChart chart) {
		Font font = new Font("宋体", Font.PLAIN, 12);
		Plot iplot=chart.getPlot();
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		DecimalFormat df1 = new DecimalFormat("#.###"+unit);//格式化  保留两位小数	
			//标题字体
			chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 16));
			//chart.setBackgroundPaint(new Color(238, 238, 255));		//背景色
			chart.setBackgroundPaint(Color.white);   //设定背景色为白色

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
		 	CategoryPlot categoryplot = chart.getCategoryPlot(); 
		 	
		 	 GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.red);//从上到下渐变的颜色
		     GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.white, 20.0F, 100.0F, Color.orange);//从上到下渐变的颜色

		 	categoryplot.setBackgroundPaint(gradientpaint1);
		 	
			//X轴标签字体
			CategoryAxis domainAxis =plot.getDomainAxis();
			domainAxis.setTickLabelFont(font);
		 	// 用于显示X轴刻度   
			domainAxis.setTickMarksVisible(true);  
			//设置X轴45度   
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			//Paint apaint[] = createPaint();						//自定义线颜色
//		 	CategoryToolTipGenerator[][] toolTips =createToolTip();	//自定义点提示
//			MyLineRender renderer = new MyLineRender(apaint);
			StandardCategoryToolTipGenerator[][] toolTips = createToolTip(); // 自定义点提示
			LineRender renderer = new LineRender(toolTips);
			
//			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();// 数据点   
//			renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}{0}={2}",df1));//map 提示信息
//			if (null != list) {
//				for (int i = 0; i < list.size(); i++) {	
//					renderer.setSeriesToolTipGenerator(i,new StandardCategoryToolTipGenerator(list.get(i).getMapTitle(),
//							NumberFormat.getPercentInstance()));				
//				}
//				
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
			
			plot.setRenderer(renderer);
		 	
		
		

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
			CategoryPlot plot = (CategoryPlot)chart.getPlot();			
			LineAndShapeRenderer  renderer = (LineAndShapeRenderer) plot.getRenderer();
			if(null !=list){
				HashMap<String, Color> colormap = ColorLibray.getColorMap();
				
				
				
//				for (int i =0; i< mn.size(); i++) {				
//					Color color = colormap.get((i+1)+"");
//					if (null == color) {
//						color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
//					}	
//					if (lastBlack && i == lastAt){
//						//基线
//						color = new Color(0,0,0);
//					}
//					
//					renderer.setSeriesPaint(i, color);		
//				}
				
				renderer.setSeriesPaint(0, Color.blue);
				renderer.setSeriesPaint(1, Color.GREEN);
				renderer.setSeriesPaint(2, Color.magenta);
				
				if (!lastBlack) {
					//气温线条颜色			
					renderer.setSeriesPaint(mn.size()-1, colormap.get("0"));
				}
				
			}
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
}
