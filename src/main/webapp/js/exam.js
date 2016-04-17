$(document).ready(function(){
			$.ajax({   
				url:'client_exam/getEpaper.action',   
				type:'get',   
				async : true, //默认为true 异步   
				dataType:'json',
			     success:function(data){  
			    	 var html = '';
			    	 $.each(data,function(i,item){
			    		 //choiceList.push(i+"");
			    		  html +='<strong>[&nbsp;'+(i+1)+'&nbsp;]&nbsp;'+item.itempoolQuestion+'</strong><br/><br/>';
			    		 html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect"  value="A"/>'+item.a+'</label>';
			    		 html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="B"/>'+item.b+'</label>';
			    		 html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="C"/>'+item.c+'</label>';
			    		 html +='<label class="radio"> <input type="radio" name="choiceList['+i+'].itempoolCorrect" value="D"/>'+item.d+'</label>';
				     });
			    	 $("#exam").append(html);
			     }
			});
		});
var examTime=20*60;
	var useTime=0,remainTime=examTime;
	
	function showCount(){
		
		if(remainTime==0){
			document.getElementById("myForm").submit();
		}
		
		useTime+=1;
		remainTime-=1;
		
		var hourU=Math.floor(useTime/3600);
		var minuteU=Math.floor((useTime-hourU*3600)/60);
		var secondU=Math.floor(useTime-hourU*3600-minuteU*60);
		document.getElementById("useTime").innerHTML=format(hourU)+":"+format(minuteU)+":"+format(secondU);
		
		var hourR=Math.floor(remainTime/3600);
		var minuteR=Math.floor((remainTime-hourR*3600)/60);
		var secondR=Math.floor(remainTime-hourR*3600-minuteR*60);
		document.getElementById("remainTime").innerHTML=format(hourR)+":"+format(minuteR)+":"+format(secondR);
	}
	
	function format(timeNumber){
		if(timeNumber<10){
			return "0"+timeNumber;
		}else{
			return timeNumber;
		}
	}
	
	window.setInterval("showCount()", 1000);