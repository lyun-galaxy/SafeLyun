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
	
	//禁止页面回退
	javascript: window.history.forward(1);
	var UnloadConfirm = {};
	UnloadConfirm.MSG_UNLOAD = "数据尚未保存\n\n您确定要离开吗？";
	UnloadConfirm.set = function(a) {
	    window.onbeforeunload = function(b) {
	        b = b || window.event;
	        b.returnValue = a;
	        return a
	    }
	};
	UnloadConfirm.clear = function() {
	    fckDraft.delDraftById();
	    window.onbeforeunload = function() {}
	};
	UnloadConfirm.set(UnloadConfirm.MSG_UNLOAD);
		function keydown() {
			if (event.keyCode == 8) {
				event.keyCode = 0;
				event.returnValue = false;
				alert("当前设置不允许使用退格键");
			}
			if (event.keyCode == 13) {
				event.keyCode = 0;
				event.returnValue = false;
				alert("当前设置不允许使用回车键");
			}
			if (event.keyCode == 116) {
				event.keyCode = 0;
				event.returnValue = false;
				alert("当前设置不允许使用F5刷新键");
			}
			if ((event.altKey)
					&& ((window.event.keyCode == 37) || (window.event.keyCode == 39))) {
				event.returnValue = false;
				alert("当前设置不允许使用Alt+方向键←或方向键→");
			}
			if ((event.ctrlKey) && (event.keyCode == 78)) {
				event.returnValue = false;
				alert("当前设置不允许使用Ctrl+n新建IE窗口");
			}
			if ((event.shiftKey) && (event.keyCode == 121)) {
				event.returnValue = false;
				alert("当前设置不允许使用shift+F10");
			}
		}