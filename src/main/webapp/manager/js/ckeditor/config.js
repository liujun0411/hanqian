/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// 界面语言，默认为 'cn'
	 config.language = 'zh-cn';
	 //工具栏是否可以被收缩
     config.toolbarCanCollapse = true;
	 //工具栏的位置
     config.toolbarLocation = 'top';//可选：bottom
     config.toolbar_Full = [
	 ['Source','-','Save','NewPage','Preview','-','Templates'],
	 ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print'],
	 ['Undo','Redo','-','SelectAll','RemoveFormat'],
	 ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	 ['Table','HorizontalRule'],
	 '/',
	 ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	 ['Styles','Format','Font','FontSize'],
	 ['TextColor','BGColor']
	 ];
};
