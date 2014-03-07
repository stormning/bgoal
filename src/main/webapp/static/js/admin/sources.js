(function($){
	$("#selectAll").click(function(){
		//var _this = this;
		$("td :checkbox").each(function(){
			//if(this!=_this){
				if($(this).attr("checked")){
					$(this).attr("checked",false);
					$(this).removeAttr("checked");
				}else{
					$(this).attr("checked",true);
				}
			//}
		});
	});
})($);