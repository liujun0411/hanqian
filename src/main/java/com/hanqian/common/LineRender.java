package com.hanqian.common;

import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;


/**
 * 线状图
 * @author clczp
 *
 */
public class LineRender extends LineAndShapeRenderer {
	private StandardCategoryToolTipGenerator toolTips[][];//各线条节点提示
	
	public LineRender(StandardCategoryToolTipGenerator[][] toolTips) {
		this.toolTips = toolTips;			
	}

	@Override
	public CategoryToolTipGenerator getToolTipGenerator(int row, int column) {			
		return toolTips[row][column];
	}
}
