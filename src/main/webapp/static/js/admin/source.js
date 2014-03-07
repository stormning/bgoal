(function($) {
	$(function($) {
		var ruleType = param.ruleType;
		var tmplate = ruleType=='GROOVY'?"#groovyTemplate":"#javascriptTemplate";

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

			$("#chooseImg").click(
					function() {
						editor.loadPlugin('image', function() {
							editor.plugin.imageDialog({
								imageUrl : $("#chooseImg img").attr("src"),
								clickFn : function(url, title, width, height,
										border, align) {
									$("#chooseImg img").attr("src", url);
									$("#hiddenUrl").val(url);
									editor.hideDialog();
								}
							});
						});
					});
		});

		ace.require("ace/commands/default_commands").commands.push({
			name : "Toggle Fullscreen",
			bindKey : "F11",
			exec : function(edt) {
				$(document.body).toggleClass("fullScreen");
				$(edt.container).toggleClass("fullScreen");
				edt.resize();
			}
		});
		var scriptEditor = ace.edit("scriptEditor");
		scriptEditor.setTheme("ace/theme/eclipse");
		if(ruleType=='GROOVY'){
			scriptEditor.getSession().setMode("ace/mode/groovy");
		}else{
			scriptEditor.getSession().setMode("ace/mode/javascript");
		}
		scriptEditor.getSession().setValue($(tmplate).text());
		scriptEditor.getSession().addEventListener("change", function() {
			$('#script').val(scriptEditor.getSession().getValue());
		});

		$("#resourceForm").validate({
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
				url:{
					required : true,
					url : true,
				},
				name:{
					required : true,
				}
			},
			messages : {
				url:{
					required:"URL必须填写",
					url:"不是有效的URL"
				},
				name:{
					required:"名称必须填写",
				}
			}
		});
		
		$("select[name=ruleType]").on("change",function(){
			if($(this).val()=='GROOVY'){
				scriptEditor.getSession().setMode("ace/mode/groovy");
				scriptEditor.getSession().setValue($('#groovyTemplate').text());
			} else {
				scriptEditor.getSession().setMode("ace/mode/javascript");
				scriptEditor.getSession().setValue($('#javascriptTemplate').text());
			}
		});
		
	});
})($);