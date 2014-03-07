(function($) {
	var searchUserUrl = $('.typeahead').attr("ajax-url");
	var nickNameIdMap = new Object();
	$('.typeahead').typeahead({
		source : function (query,process){
			$.getJSON(searchUserUrl+"&keyword="+query,function(res){
				if(res&&res.length>0){
					var items = new Array();
					for(var index in res){
						items.push(res[index].nickName);
						nickNameIdMap[res[index].nickName] = res[index].id;
					}
					process(items);
				}
			});
		},
		updater : function(item){
			var _href = location.href;
			var _creator = nickNameIdMap[item];
			var reg=new RegExp("(.+\?.*creator=)(\\d{1,})(.*)","gmi");
			if(_href.match(reg)){
				_href = _href.replace(reg,"$1"+_creator+"$3");
			}else if(_href.indexOf("?")==-1){
				_href+="?creator="+_creator;
			}else{
				_href+="&creator="+_creator;
			}
			location.href=_href;
		}
	});
})($);