var  highlightcolor='#eafcd5';
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
	var i;
	for(i=0;i<cs.length;i++){
	    cs[i].style.backgroundColor=highlightcolor;
	}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	var i;
	for(i=0;i<cs.length;i++){
	    cs[i].style.backgroundColor="";
	}
}

function isDate(date) {
	var validDate = "((19|20)?[0-9]{2})[- /.](0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])";
	return date != undefined && date.match(validDate);
}
function checkTime(time) {
	var validTime ="([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
	//var reg = "/^(10|11|12|0?[1-9]):[0-5][0-9]$/";
	return time != undefined && time.match(validTime);
}