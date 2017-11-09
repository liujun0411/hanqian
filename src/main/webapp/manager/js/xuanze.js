    $("document").ready(function(){
    
     $("#all").click(function(){  
    if(this.checked){  
        $("input[name='checkbox']").each(function(){this.checked=true;});
 
    }else{  
        $("input[name='checkbox']").each(function(){this.checked=false;});  
        
    }  
 }); 

     $("#btn1").click(function(){
        
       $("[name='checkbox']").attr("checked",'true');//全选
     
     })
     //$("#btn2").click(function(){
         
         //$("[name='checkbox']").removeAttr("checked");//取消全选
     
    //})
       //$("#btn3").click(function(){
         
        //$("[name='checkbox']:even").attr("checked",'true');//选中所有奇数
     
     // })
       $("#btn4").click(function(){
         
         $("[name='checkbox']").each(function(){
            
           
             if($(this).attr("checked"))
            {
              $(this).removeAttr("checked");
             
            }
           else
           {
             $(this).attr("checked",'true');
              
          }
         
         })
      
       })
        //$("#btn5").click(function(){
      //var str="";
         // $("[name='checkbox'][checked]").each(function(){
           //  str+=$(this).val()+"\r\n";
        // })
       //alert(str);
   // })
		
     $("#all2").click(function(){  
    if(this.checked){  
        $("input[name='checkbox2']").each(function(){this.checked=true;});
 
    }else{  
        $("input[name='checkbox2']").each(function(){this.checked=false;});  
        
    }  
 }); 

     $("#btn12").click(function(){
        
       $("[name='checkbox2']").attr("checked",'true');//全选
     
     })
     //$("#btn2").click(function(){
         
         //$("[name='checkbox']").removeAttr("checked");//取消全选
     
    //})
       //$("#btn3").click(function(){
         
        //$("[name='checkbox']:even").attr("checked",'true');//选中所有奇数
     
     // })
       $("#btn42").click(function(){
         
         $("[name='checkbox2']").each(function(){
            
           
             if($(this).attr("checked"))
            {
              $(this).removeAttr("checked");
             
            }
           else
           {
             $(this).attr("checked",'true');
              
          }
         
         })
      
       })		
		
		
		
		
      })
	
	
	
	
	
	
	
	
	
	
	
	
	
	
