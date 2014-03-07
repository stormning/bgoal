(function($) {
	$(function($) {
		KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="info"]', {
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

		$("#userForm").validate({
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
				email:{
					required : true,
					email : true,
				},
				nickName:{
					required : true,
				}
			},
			messages : {
				email:{
					required:"email必须填写",
					url:"不是有效的email"
				},
				nickName:{
					required:"昵称必须填写",
				}
			}
		});
	});
})($);