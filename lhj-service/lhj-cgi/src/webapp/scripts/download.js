/*
 * (C) Fuji Xerox Co., Ltd. 2015-2016
 *
 * $Id$
 */

(function($) {
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				o[this.name] += "," + (this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
})(jQuery);

$(function() {
	$("body").xuxPage(/* {width: "full"} */);
	var article = $("#indexArticle").xuxBoundingBox()
			.find("a[href]").xuxHyperlink().end()
			.find("section").slice(0, -1).xuxApplyBoxAndLayout({
				getBoxOptions: function(options) {
					return $(this).is(".leading") ? $.extend({}, options, {
						labelLayout: "leading"
					}) : options;
				},
				subordinates: {
					enable: {
						"#useBasgGlobalization": ".basgCulture",
						"#useEasgGlobalization": ".easgCulture"
					}
				}
			}).end().last().xuxApplyBoxAndLayout({
				horizontalLayout: "trailing"
			}).end().end();
	$(".divToggleAll").css({
		"margin-bottom": (-$(".divToggleAll").last().height() - parseInt($(".divToggleAll").css("margin-top"))) + "px"
	});
	$("#sectionBasgComps, #sectionEasgComps").find(".columnLeft").xuxIndent().end()
			.find(".leading, .divToggleAll").xuxIndent({depth: 2});
	function changeStatus(checkbox, status) {
		checkbox.prop("checked", status).xuxCheckbox("refresh");
	}
	changeStatus($("#compAll"), true);
	$("#compAll").change(function() {
		var me = $(this);
		changeStatus($("#indexArticle").find(":checkbox").not(me), me.prop("checked"));
		$("#style_Basg, #style_Easg").change();
	}).change();
	var toggleAllSelector = ":checkbox[value='all']";
	$(".columnLeft").find(":checkbox").change(function() {
		var me = $(this), columnLeft = me.closest(".columnLeft"), allChecked;
		if (me.is($(toggleAllSelector))) {
			changeStatus(me.closest("section").find(":checkbox").not(me), me.prop("checked"));
			if (me.is("#style_All")) {
				$("#style_Basg, #style_Easg").change();
			}
		} else {
			allChecked = !columnLeft.find(":checkbox:unchecked").not(toggleAllSelector).length;
			changeStatus(columnLeft.find(toggleAllSelector), allChecked);
			if (me.is($("#style_Basg"))) {
				article.find("section").slice(4, 10).find(":checkbox")
						.xuxCheckbox(me.prop("checked") ? "enable" : "disable");
				$("#useBasgGlobalization").change();
			} else if (me.is($("#style_Easg"))) {
				article.find("section").slice(10, 15).find(":checkbox")
						.xuxCheckbox(me.prop("checked") ? "enable" : "disable");
				$("#useEasgGlobalization").change();
			}
		}
		initCheckBox(me);
	}).change();
	function initCheckBox(checkbox) {
		var checkedLength;
		if (checkbox.is($("#style_Basg")) && checkbox.prop("checked")) {
			if (!$("#sectionBasgSize").find(":checkbox:checked").length) {
				changeStatus($("#basgSizeNormal"), true);
			}
			if (!$("#sectionAdditionalStyle").find(":checkbox:checked").length) {
				changeStatus($("#extStyleBasgFXLogo"), true);
			}
		}
		if (checkbox.is($("#style_Easg")) && checkbox.prop("checked") &&
				!$("#sectionEasgVer").find(":checkbox:checked").length) {
			changeStatus($("#easgVerV4"), true);
		}
		checkedLength = article.find(":checkbox:checked").filter(function() {
			return !$(this).prop("disabled") && this.id !== "compAll";
		}).length;
		$("#download").xuxButton(checkedLength ? "enable" : "disable");
	}
	function checkDownLoad() {
		var msg = "",
			basgCompsLength = $("#sectionBasgComps").find(":checkbox:checked").length,
			basgSizeLength = $("#sectionBasgSize").find(":checkbox:checked").length,
			basgAdditionalStyle = $("#sectionAdditionalStyle").find(":checkbox:checked").length,
			easgVer = $("#sectionEasgVer").find(":checkbox:checked").length,
			easgCompsLength = $("#sectionEasgComps").find(":checkbox:checked").length;
		if ($("#style_Basg").prop("checked")) {
			if (!basgSizeLength) {
				msg += "Please select a BASG style size at least!";
			} else if (!basgAdditionalStyle) {
				msg += "Please select a BASG additional style at least!";
			}
		}
		if ($("#style_Easg").prop("checked") && !easgVer) {
			msg += "Please select an EWB version at least!";
		}
		if (msg.length) {
			alert(msg);
			return false;
		}
		return true;
	}
	$(".divToggleAll").find(":checkbox").change(function() {
		var me = $(this), div = me.closest(".divToggleAll"),
			section = me.closest("section"),
			allChe = section.find(".columnLeft :checkbox[value='all']");
		changeStatus(div.next(".leading").find(":checkbox"), me.prop("checked"));
		changeStatus(allChe, !section.find(":checkbox:unchecked").not(allChe).length);
	});
	$(".leading").find(":checkbox").change(function() {
		var me = $(this),
			leading = me.closest(".leading"),
			section = leading.closest("section"),
			allChecked, dependsOn = [], datas,
			checkAll = section.find(".columnLeft :checkbox[value='all']");
		allChecked = !leading.find(":checkbox:unchecked").length;
		changeStatus(leading.prev(".divToggleAll").find(toggleAllSelector), allChecked);
		changeStatus(checkAll, !section.find(":checkbox:unchecked").not(checkAll).length);
		if ((datas = me.data("data-depended")) && !me.prop("checked")) {
			$.each($.unique(datas), function(index, value) {
				var che = section.find(":checkbox[value='" + $.trim(value) + "']");
				changeStatus(che, false);
				che.change();
			});
		}
		if (me.attr("data-dependencies") && me.prop("checked")) {
			$.each(me.attr("data-dependencies").split(","), function(index, value) {
				var che, data;
				dependsOn.push(section.find(":checkbox[value='" + $.trim(value) + "']"));
				che = dependsOn[index];
				changeStatus(che, true);
				che.change();
				if (data = che.data("data-depended")) {
					data.push(me.val());
				} else {
					che.data("data-depended", [me.val()]);
				}
			});
		}
		initCheckBox(me);
	});

	var timer = null, internal = 1000,
		downloadRequest = null,
		process = $("#process").xuxProgressModalWindow({ value: false });

	function checkStatus(data) {
		$("#buildNum").val(data.buildNum);
		if (!data.status || data.status === "processing") {
			timer = setTimeout(getStatus, internal);
		} else if (data.status === "complete") {
			$("#downloadForm").submit();
			process.xuxProgressModalWindow("close");
		} else if (data.status === "error") {
			process.xuxProgressModalWindow("close");
			// TODO
			alert("error occurs!" + data.errorReason);
		}
	}
	function getStatus() {
		$.ajax({
			url: "status",
			method: "GET",
			data: {
				buildNum: $("#buildNum").val()
			},
			dataType: "json",
			success: function(data) {
				checkStatus(data);
			},
			error: function(error) {
				process.xuxProgressModalWindow("close");
				// TODO
				console.log("error " + error);
			}
		});
	}
	$("#download").click(function() {
		if (checkDownLoad()) {
			process.xuxProgressModalWindow("open");
			downloadRequest = $.ajax({
				url: "download",
				method: "post",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify($("#paramForm").serializeObject()),
				dataType: "json",
				success: function(data) {
					checkStatus(data);
				},
				error: function(error) {
					process.xuxProgressModalWindow("close");
					// TODO
					console.log("error " + error);
				}
			});
		}
	});

	$("#btnCancel").click(function () {
		if (timer != null) {
			clearTimeout(timer);
		}
		if (downloadRequest != null) {
			downloadRequest.abort();
		}
		process.xuxProgressModalWindow("close");
	});

});