$(function() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	

	var accordion = new Accordion($('#accordion'), false);
	
	
});
function accordion(){
	
	$("#accordion").pin({
		padding : {
			top : 45,
			bottom : 10
		},
		minWidth : 940
	});
	
}
$("#accordion").pin({
	padding : {
		top : 45,
		bottom : 10
	},
	minWidth : 940
});

function getSubsectionContent(url) {
	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/SafeLyun/score.json',
		dataType : 'json',
		cache : false,
		success : function(data) {
			$(".jumbotron").empty().append(
					"<h1>" + data.subsectionName + "</h1>");
			$("#content").empty().append(data.subsectionContent);
			clearInterval(s);
			clearInterval(t1);
			clearInterval(t2);
			$(".pie2").css("-o-transform","rotate(0deg)");
			$(".pie2").css("-moz-transform","rotate(0deg)");
			$(".pie2").css("-webkit-transform","rotate(0deg)");
			$(".pie1").css("-o-transform","rotate(0deg)");
			$(".pie1").css("-moz-transform","rotate(0deg)");
			$(".pie1").css("-webkit-transform","rotate(0deg)");
			i = 0;
			j = 0;
			MM = data.mm;
			SS = data.ss;
			MS = 9;
			a = MM*60*10+SS*10+MS;

			a = 180/(a/2);
			MM = "0" + MM;
			
			s = setInterval("showTime()",100);
			t1 = setInterval("start1()",100);
			accordion();
		},
		error : function() {
			alert('error');
		}
	});
	
}
var i = 0;
var j = 0;
var t1,t2,s,MM,SS,MS,a,totle; 

function initTime(m,s) {
	MM = m;
	SS = s;
	MS = 9;
	i = 0;
	j = 0;
	a = MM*60*10+SS*10+MS;
	a = 180/(a/2);
	totle = (MM+1)*600;
	MM = "0" + MM;
}
function showTime(){
	totle = totle - 1;
	if(totle==0){
		clearInterval(s);
		clearInterval(t1);
		clearInterval(t2);
		$(".pie2").css("-o-transform","rotate(" + d + "deg)");
		$(".pie2").css("-moz-transform","rotate(" + d + "deg)");
		$(".pie2").css("-webkit-transform","rotate(" + d + "deg)");
	}else{
		if(totle>0 && MS>0){
			MS = MS - 1;
			if(MS < 10){MS = "0" + MS};
		};
		if(MS==0 && SS>0){
			MS = 10;
			SS = SS - 1;
			if(SS < 10){SS = "0" + SS};
		};
		if(SS==0 && MM>0){
			SS = 60;
			MM = MM - 1;
			if(MM < 10){MM = "0" + MM};
		};
	};
	$(".time span").html(MM + ":" + SS + ":" + MS);
};
s = setInterval("showTime()",100);
function start1(){
	i = i + a;
	
	if(i>180){
		j = 0;
		clearInterval(t1);
		t2 = setInterval("start2()",100);
		i=180;
	};
	$(".pie1").css("-o-transform","rotate(" + i + "deg)");
	$(".pie1").css("-moz-transform","rotate(" + i + "deg)");
	$(".pie1").css("-webkit-transform","rotate(" + i + "deg)");
};
function start2(){
	j = j + a;
	if(j>180){
		i = 0;
		clearInterval(t2);
		j=180;
	};
	$(".pie2").css("-o-transform","rotate(" + j + "deg)");
	$(".pie2").css("-moz-transform","rotate(" + j + "deg)");
	$(".pie2").css("-webkit-transform","rotate(" + j + "deg)");
};
t1 = setInterval("start1()",100);