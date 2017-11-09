package com.hanqian.util;


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
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.hanqian.common.ChartData;



public class OneUseBiLiChart {
	private List<ChartData> list=null;		//图表数据集
	private String title=null;				//标题
	
	class CustomBarRenderer extends BarRenderer
	{

		private Paint colors[];
		private CategoryToolTipGenerator toolTips[][];//各柱子提示
		
		@Override
		public Paint getItemPaint(int i, int j)
		{
			return colors[j % colors.length];
		}

		@Override
		public CategoryToolTipGenerator getToolTipGenerator(int row, int column) {
			return toolTips[row][column];
		}

		public CustomBarRenderer(Paint apaint[],CategoryToolTipGenerator[][] toolTips)
		{
			this.colors = apaint;
			this.toolTips=toolTips;
		}
	}
	
	
	public OneUseBiLiChart(List<ChartData> list,String title){
		this.list = list;
		this.title = title;
	}
	
	public JFreeChart getChart() {
		JFreeChart chart = ChartFactory.createBarChart(title,// 图表标题 
				null,// 目录轴的显示标签 
				null, // 数值轴的显示标签 
				this.getDataSet(),//数据
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, //是否显示下边 标签
				true, // 是否生成工具 
				false);// 是否生成URL链接 

		this.configFont(chart); // 设置字体(中文乱码问题)
//		this.setDataColor(chart); //设置数据区颜色
		
		return chart;
	}
	
	
	
	
	private void configFont(JFreeChart chart){
		Font font = new Font("宋体", Font.PLAIN, 14);
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		
		TextTitle textTitle=chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 16));//     标题字体
 
	 	//标签字体
		chart.getLegend().setItemFont(font);
		//自定义标签字体		
        chart.getLegend().setVisible(false);//不显示原下边标签
        
        //Y轴标签
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();   
	 	numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());//设置y轴显示数据类型
	 	DecimalFormat df1 = new DecimalFormat("#.##%");//格式化  保留两位小数
	 	numberAxis.setNumberFormatOverride(df1);
	 	numberAxis.setTickLabelFont(font);//字体
//	 	numberAxis.setAutoTickUnitSelection(false); //自设刻度
//      NumberTickUnit   nt=   new   NumberTickUnit(5D);         
//      numberAxis.setTickUnit(nt);	
//	 	numberAxis.setVisible(false);//是否可见
	 	
	 	
	 	
        //X轴标签字体
		CategoryAxis domainAxis =plot.getDomainAxis();
		domainAxis.setTickLabelFont(font);	 	
		domainAxis.setTickMarksVisible(true);  // 用于显示X轴数据   		
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//设置X轴45度   
	
		
		
	 	
//		render.setDrawBarOutline(false);		
//		render.setBaseItemLabelGenerator(new LabelGenerator());
//		render.setBaseItemLabelsVisible(true);
	 	
	 	
	 	Paint apaint[] = createPaint();							//自定义柱子颜色
	 	CategoryToolTipGenerator[][] toolTips =createToolTip();	//自定义柱子提示
		CustomBarRenderer render = new CustomBarRenderer(apaint,toolTips);
		render.setItemMargin(0.01D);//单组柱子间距
//		render.setBarPainter(new StandardBarPainter());
//		render.setDrawBarOutline(true);
//		render.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
//		if (null != list && list.size() <=24) {
//			render.setMaximumBarWidth(0.05D); //柱子宽度
//		}
		
	
//		//map 自定义提示信息
//		this.setMapTitle(render);		
//		
//		
		//柱子展示数据
		//render.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}"+zChart,NumberFormat.getNumberInstance()));
		render.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",df1));
		render.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));//展示位置
//		render.setBaseItemLabelsVisible(true);
		render.setBaseItemLabelFont(font);		
		plot.setRenderer(render);
		

		
		
//		categoryplot.setBackgroundAlpha(1f);  //数据区的背景透明度 1不透明 ,0 透明		 

		
	}
	
	
	/**
	 * 梆定数据集
	 * @return
	 */
	private DefaultCategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (null != list) {
			for (ChartData obj:list) {
				dataset.addValue(obj.getValue().doubleValue()/100, obj.getRowKey(), obj.getColumnKey());				
			}
		}
		
		return dataset;
	}
	
	/**
	 * 设置分组颜色
	 * @return
	 */
	private Paint[] createPaint()
	{
		Paint[] apaint = null;
		if(null !=list){
			apaint = new Paint[list.size()];
			Color color=null;
			HashMap<String,Color> cmap  = ColorLibray.getColorMap();
			for (int i =0; i< list.size(); i++) {
					if(cmap.get(list.get(i).getId())!=null && i==0){
							color = cmap.get(list.get(i).getId());//标准颜色
					}else{
							color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));//各医院颜色
					}
					apaint[i] = color;				
			}
					
		}		
		
		return apaint;
	}
	
	
	/**
	 * 设置单柱提示信息
	 * @param barRend
	 */
	private CategoryToolTipGenerator[][] createToolTip(){
		CategoryToolTipGenerator[][] tools=null;
		if (null != list) {
			tools=new CategoryToolTipGenerator[1][list.size()];
			for (int i = 0; i < list.size(); i++) {	
				tools[0][i]=new StandardCategoryToolTipGenerator(list.get(i).getMapTitle(),NumberFormat.getPercentInstance());
				//barRend.setSeriesToolTipGenerator(i,new StandardCategoryToolTipGenerator(list.get(i).getMapTitle(),NumberFormat.getPercentInstance()));				
			}
			
		}
		return tools;
	}
	

	
	/**
	 * 设置数据区颜色
	 * 
	 * @param chart
	 * @param list
	 */
	private void setDataColor(JFreeChart chart) {
		//柱体颜色设置
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		BarRenderer render = (BarRenderer) plot.getRenderer(); 
		if(null !=list){
			for (int i = 0; i < list.size(); i++) {				
				//if (i !=0) {
					HashMap<String,Color> cmap = this.getColor();
					Color color=null;
					if(cmap.get(i+"")==null){
							color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
					}else{
							color = cmap.get(i+4+"");
					}
					
					render.setSeriesPaint(i,color);					
				//}
					
			}			
		}		
	}
	
	/**
	 * 颜色库
	 * @return
	 */
	private HashMap<String,Color> getColor(){
		HashMap<String, Color> map= new HashMap<String, Color>();
		map.put("0", new Color(0,102,153));
		map.put("1", new Color(217,149,148));
		map.put("2", new Color(102,255,102));
		map.put("3", new Color(255,255,0));		
		map.put("4", new Color(102,204,255));
		map.put("5", new Color(255, 102, 153));
		map.put("6", new Color(0, 255, 153));		
		map.put("7", new Color(153, 0, 51));
		map.put("8", new Color(255, 0, 102));
		map.put("9", new Color(255, 153, 51));		
		map.put("10", new Color(51, 102, 255));
		map.put("11",new Color(112, 48, 160));
		return map;
	}
	

}
