(function($) {
	$(function($) {
		KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="content"]', {
				items : [ 'bold', 'italic', 'underline', 'strikethrough',
						'removeformat', '|', 'insertorderedlist',
						'insertunorderedlist', 'forecolor', 'hilitecolor',
						'fontname', 'fontsize', '|', 'link', 'unlink',
						'emoticons', 'shcode', 'image', 'flash', 'quote', '|',
						'code', 'source', 'about' ],
				cssPath : param.cssPath,
				uploadJson : param.uploadJson,
				filePostName : 'file',
				fileManagerJson : param.fileManagerJson,
				allowFileManager : true,
				width : '80%'
			});
			prettyPrint();
		});

		$("#articleForm").validate({
			errorElement : "span",
			errorPlacement : function(error, element) {
				error.appendTo(element.parents("div.controls"));
				error.addClass("help-block");
				element.parents(".control-group").removeClass("success").addClass("error");
			},
			success : function(label) {
				label.parents(".control-group").removeClass("error");
			},
			rules : {
				title:{
					required : true,
				},
				content:{
					required : true,
				}
			},
			messages : {
				title:{
					required:"标题必须填写",
				},
				content:{
					required:"内容必须填写",
				}
			}
		});
		
		$("#current,#fake").click(function(){
			$('.typeahead').hide();
		});
		
		$("#special").click(function(){
			$('.typeahead').show();
		});
		
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
				var _creator = nickNameIdMap[item];
				$("#special").val(_creator);
				return item;
			}
		});
		
		
	});
})($);