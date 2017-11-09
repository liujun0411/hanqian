package com.hanqian.util;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;


/**
 * 饼形图
 * @author czpsky
 *
 */
public class PieChart {
	private List list=null;
	
	
	public PieChart(List datalist){
		list = datalist;
	}
	
	/**
	 * 获得饼形图
	 * @return
	 */
	public  JFreeChart getChart(){
		JFreeChart chart = ChartFactory.createPieChart3D(null, // 图表标题
			this.getDataSet(), 
			true, 
			false, 
			false);
		PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();
		pieplot3d.setStartAngle(180D);
		pieplot3d.setDirection(Rotation.ANTICLOCKWISE);
		pieplot3d.setForegroundAlpha(0.9F);
		
		this.configFont(chart);		//设置字体(中文乱码问题)		
		this.setDataColor(chart);	//设置扇区颜色
		
		return chart;
	}
	
	
	/**
	 * 饼形图 数据
	 * @return
	 */
	private  DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		if(list != null){
			Map map = null;
			Double value= null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map)list.get(i);
				try {
					value = Double.parseDouble(map.get("area").toString());
					if (value>0) {
						dataset.setValue(map.get("name").toString()+":",value);
					}
					
				} catch (Exception e) {
					// TODO: handle exception					
				}
				
			}
		}		
		return dataset;
	}
	
	/**
	 * 设置字体
	 * @param chart
	 */
	private  void configFont(JFreeChart chart) {
		 	Font font = new Font("宋体", Font.PLAIN, 13);
	        //底部标题字体
	        chart.getLegend().setItemFont(font);
	        
	        
	      
		 	
	        // 饼图各扇区的字体 setNumberFormatOverride
	        PiePlot3D pieplot = (PiePlot3D) chart.getPlot();
	        pieplot.setLabelFont(new Font("宋体", Font.PLAIN, 14));	//字体		
	        pieplot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);	//线条
	        pieplot.setLabelOutlinePaint(new Color(255, 255, 255)); //边框
	        pieplot.setLabelGap(0);	
	        
	        DecimalFormat df1 = new DecimalFormat("#.##%");//格式化  保留两位小数
//	        NumberAxis numberAxis = (NumberAxis) pieplot.getRootPlot();   
//		 	numberAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());//设置y轴显示数据类型
//		 	numberAxis.setNumberFormatOverride(df1);
//		 	numberAxis.setTickLabelFont(font);//字体
		 	
	        //无数据提示
			pieplot.setNoDataMessage("查无数据!");
			pieplot.setNoDataMessageFont(new Font("宋体", Font.PLAIN, 20));
			//背景颜色设置
			pieplot.setBackgroundPaint(new Color(255, 255, 255));
			//边框不显示
			pieplot.setOutlineVisible(false);
			
			//显示的信息 {0} name  {1} value  {2}百份比
			pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}{2}", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));

			//pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}{2}"));		
			//文字背影颜色
			pieplot.setLabelBackgroundPaint(new Color(255, 255, 255));		
			//pieplot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("{1}"));		
			//扇区文字位置 true 内  false 外
			pieplot.setSimpleLabels(false);		
			//分离扇区  与文字的 比例 
			pieplot.setInteriorGap(0.1D);
			// 设置空值,0值,负值是否显示出来,如果显示的话就是false
			pieplot.setIgnoreNullValues(true);
			pieplot.setIgnoreZeroValues(true);
	}
	
	/**
	 * 设置扇区颜色
	 * @param chart
	 * @param list
	 */
	private  void setDataColor(JFreeChart chart){		
		PiePlot pieplot = (PiePlot)chart.getPlot();
		if(list != null){
			Map map = null;
			Color color = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map)list.get(i);
				if(getColor().get((i+1)+"")==null){
					color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
				}else{
					color = this.getColor().get((i+1)+"");
				}
				pieplot.setSectionPaint(map.get("name").toString(),color);
			}
		
			
			    
	        		
			//选中的项
			try{
				String at =((Map)list.get(0)).get("atname").toString();
				if(!SysUtil.isNull(at)){
					pieplot.setExplodePercent(at, 0.2D);
				}
			}catch(Exception e){}
			
			
		}
		
		
	}
	
	/**
	 * 颜色库
	 * @return
	 */
	private Map<String,Color> getColor(){
		Map<String, Color> map= new HashMap<String, Color>();
		map.put("1", new Color(121,177,165));
		map.put("2", new Color(239,221,172));
		map.put("3", new Color(139,179,212));		
		map.put("4", new Color(222,127,59));
		map.put("5", new Color(102, 124, 180));
		map.put("6", new Color(166, 140, 135));		
		map.put("7", new Color(172, 181, 123));
		map.put("8", new Color(214, 227, 103));
		map.put("9", new Color(220, 181, 89));		
		map.put("10", new Color(221, 131, 161));
		map.put("11",new Color(249, 196, 123));		
		return map;
	}	
	
}
