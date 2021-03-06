$(function() {
	var $slide_list = $("#slide_list"),
		$slide_confirm_btn = $("#slide_confirm_btn"),
		$silde_id = $("#silde_id"),
		$slide_photo = $("#slide_photo"),
		$slide_name = $("#slide_name"),
		$slide_link = $("#slide_link"),
		$slide_description = $("#slide_description"),
		$slide_photo_box = $("#slide_photo_box"),
		$slide_photo_description = $("#slide_photo_description"),
		$slideshow_control_btn = $(".slideshow_control_btn"),
		$slideshow_modify_modal = $("#slideshow_modify_modal"),
		$slideshow_modify_modal_title = $("#slideshow_modify_modal_title"),
		$slideshow_modify_form = $("#slideshow_modify_form"),
		$slide_modify_confirm = $("#slide_modify_confirm"),
		$slideshow_select_modal = $("#slideshow_select_modal"),
		$slideshow_select_modal_title = $("#slideshow_select_modal_title"),
		$slide_select_confirm = $("#slide_select_confirm"),
		$slideshow_control_insert = $("#slideshow_control_insert"),
		$slideshow_control_update = $("#slideshow_control_update"),
		$slideshow_control_changeorder = $("#slideshow_control_changeorder"),
		$slideshow_control_delete = $("#slideshow_control_delete"),
		$slideNameError = $("#slideNameError"),
		$slideLinkError = $("#slideLinkError"),
		$slideDescriptionError = $("#slideDescriptionError"),
		$slidePhotoError = $("#slidePhotoError"),
		singleSelected = false;
	
	// init slide control btn
	$slideshow_control_btn.disableSelection();
	$slideshow_select_modal.disableSelection();
	$slide_list.sortable().sortable("disable").selectable({selected: function(event, ui){
		var $selected = $(ui.selected);
		$selected.toggleClass('slide_selected');
		if(singleSelected){
			$selected.siblings().removeClass('slide_selected');
		}
	}}).selectable("disable");
	
	/* init drag event */
    function dragEnter(e) {
    	$slide_photo_box.addClass("drag_mask");
    	$slide_photo_description.addClass("transparent");	
    }
    function dragLeave(e) {
    	$slide_photo_box.removeClass("drag_mask");
    	$slide_photo_description.removeClass("transparent");	
    }
    function dropHandler(e) {
    	$slide_photo_box.removeClass("drag_mask");
	    $slide_photo_description.addClass("hide");	
    }
    $(function () {
    	$slide_photo_box.on("dragenter", dragEnter).on("dragleave", dragLeave).on("drop", dropHandler);
    });
    /* init drag event end */
    
	// reset modify form
	function resetModifyForm() {
		$slideNameError.html("");
		$slideLinkError.html("");
		$slideDescriptionError.html("");
		$slidePhotoError.html("");
		$slide_photo_description.removeClass("hide").removeClass("transparent");	
		$slideshow_modify_form[0].reset();
	}
	
	/* input file read photo */
	 function readURL(input) {
   	  if (input.files && input.files[0]) {
   	    var reader = new FileReader();
   	    reader.onload = function(e) {
   	    	$slide_photo_box.css('background-image', "url("+e.target.result+")").attr({"data-instruction":"點擊更換圖片","data-drag-instruction":"托放置此更換圖片"});
	   	    $slide_photo_description.addClass("hide");
   	    }
   	    reader.readAsDataURL(input.files[0]);
   	  }
   	}
    
	 $slide_photo.on("change",function() {
   	  readURL(this);
   	});
	/* input file read photo end*/
	
	/* refresh slideshow list */
	function refreshSlideshowList() {
		$.getJSON(ctx + "/slideshow/getSlides",function(data){
			if(!data.errMsg){
			var $docFrag = $(document.createDocumentFragment());
			$.each(data,function(index,value){			
				$docFrag.append("<li class='list-group-item' data-slide-photo='"+value.slide_photo+"' data-slide-link='"+value.slide_link+"' data-slide-description='"+value.slide_description+"' value='"+value.slide_id+"'>"+value.slide_name+"</li>");
			});
			$slide_list.html("").append($docFrag);}
			else{
				alert(data.errMsg);
			}
		});
	}
	
	$slideshow_select_modal.on("show.bs.modal",refreshSlideshowList);
	/* refresh slideshow list end */
	 	
    /* inset slideshow */
    function insertSlide(){
    	$slide_modify_confirm.prop("disabled",true);
    	var formData = new FormData($slideshow_modify_form[0]);
    	$.ajax({
			type : 'POST',
			url : ctx + "/slideshow/add",
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				if(data.success){
	    			alert(data.success);
	        		$slideshow_modify_modal.modal("hide");
	        		refreshSlideshowList();
	    		}else{
	    			if(data.errMsg){
	    				alert(data.errMsg);
	    			}
					$slideNameError.html(data.nameErr || "");
					$slideLinkError.html(data.linkErr || "");
					$slideDescriptionError.html(data.descriptionErr || "");
					$slidePhotoError.html(data.photoErr || "");
	    		}
				$slide_modify_confirm.prop("disabled",false);
			}
		});	
    }
       	
	$slideshow_control_insert.click(function(e){
		$slideshow_modify_modal_title.text("新增輪播圖");
		$slide_modify_confirm.text("確認新增");   
		$slide_modify_confirm.on("click",insertSlide);
		$slide_photo_box.attr({"data-instruction":"點擊上傳圖片","data-drag-instruction":"托放置此上傳圖片"});
		$slideshow_modify_modal.modal("show")
		.one("hidden.bs.modal", function (e) {
			$slide_modify_confirm.off("click",insertSlide);
   	    	$slide_photo_box.removeAttr("style");
   	    	resetModifyForm();
		});
	});
    /* inset slideshow end */
    
	/* update slideshow */
	function updateSlide() {
    	$slide_modify_confirm.prop("disabled",true);
		var formData = new FormData($slideshow_modify_form[0]);
		$.ajax({
			type : 'POST',
			url : ctx + "/slideshow/update",
			data : formData,
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				if(data.success){
	    			alert(data.success);
	        		$slideshow_modify_modal.modal("hide");
	        		refreshSlideshowList();
	    		}else{
	    			if(data.errMsg){
	    				alert(data.errMsg);
	    			}
	    			if(data.idErr){
	    				alert(data.idErr);
	    			}
					$slideNameError.html(data.nameErr || "");
					$slideLinkError.html(data.linkErr || "");
					$slideDescriptionError.html(data.descriptionErr || "");
					$slidePhotoError.html(data.photoErr || "");
	    		}
		    	$slide_modify_confirm.prop("disabled",false);
			}
		});	
	}
    
    function fillModifyForm(src) {
    	$slide_photo_description.addClass("hide");
    	$silde_id.val(src.val());
    	$slide_photo_box.css('background-image', "url("+src.data("slide-photo")+")").attr({"data-instruction":"點擊更換圖片","data-drag-instruction":"托放置此更換圖片"});
    	$slide_name.val(src.text());
    	$slide_link.val(src.data("slide-link"));
    	$slide_description.val(src.data("slide-description"));
	}
	
	$slideshow_control_update.click(function(){
		$slideshow_select_modal_title.text("請選擇要修改的輪播圖");
		$slide_list.selectable("enable");
		singleSelected = true;
		$slideshow_select_modal.modal("show")
		.one("hidden.bs.modal", function (e) {
			$slide_list.selectable("disable").find("li").removeClass("slide_selected");
			$slide_select_confirm.off("click");
			singleSelected = false;
		});
		$slide_select_confirm.text("確認選擇").on("click",function(e){
			if($(".slide_selected").length == 1){
			fillModifyForm($(".slide_selected").first());
			$slideshow_select_modal.modal("hide")
			.one("hidden.bs.modal", function (e) {
				$slideshow_modify_modal_title.text("修改輪播圖");
				$slide_modify_confirm.text("確認修改").on("click",updateSlide);
				$slide_select_confirm.off("click");
				$slideshow_modify_modal.modal("show")
				.one("hidden.bs.modal", function (e) {
					$slide_modify_confirm.off("click",updateSlide);
		   	    	$slide_photo_box.removeAttr("style");
		   	    	resetModifyForm();
				});
			});
			}else{
				alert("沒有選擇任何項目");
			}	
		});
	});
	/* update slideshow end*/
	
	/* change slideshow order */
	 function changeSlideOrder(){
	    	$slide_select_confirm.prop("disabled",true);
	    	var i = 1,
	    	orderMap = new Object;
	    	$slide_list.find('li').each(function(){
	    		orderMap[$(this).val()] = i++ ;
	    	});
	    	var sildeOrderMap = {"orderMap":orderMap}
	    	$.getJSON(ctx + "/slideshow/changeOrder",{"sildeOrderMap":JSON.stringify(sildeOrderMap)},function(data){
	    		if(data.success){
	    			alert(data.success);
	    			$slideshow_select_modal.modal("hide");
	    		}else if(data.errMsg){
	    			alert(data.errMsg);	    			
	    		}
		    	$slide_select_confirm.prop("disabled",false);
	    	});	    	
	    }
	
	$slideshow_control_changeorder.click(function(e){
		$slideshow_select_modal_title.text("請重新調整輪播圖順序");
		$slide_select_confirm.text("確認調整").on("click",changeSlideOrder);
    	$slide_list.sortable("enable");   	
		$slideshow_select_modal.modal("show")
		.one("hidden.bs.modal", function (e) {
			$slide_select_confirm.off("click",changeSlideOrder);
			$slide_list.sortable("disable");
			refreshSlideshowList();
		});
	});
	/* change slideshow order end*/
	
	/* delete slideshow */
	function deleteSlide() {
		var idList = [];
		$(".slide_selected").each(function(){
			idList.push($(this).val());
		});
		if(idList.length > 0){
			$slide_select_confirm.prop("disabled",true);
    	var slideIdList = {"idList":idList}
			$.getJSON(ctx + "/slideshow/remove",{"slideIdList":JSON.stringify(slideIdList)},function(data){
	    		if(data.success){
	    			alert(data.success);
	    			$slideshow_select_modal.modal("hide");
					refreshSlideshowList();
	    		}else if(data.errMsg){
	    			alert(data.errMsg);	    			
	    		}
		    	$slide_select_confirm.prop("disabled",false);
	    	});	  
		}else{
			alert("你沒有選擇任何要刪除的項目");
		}
	}
	
	$slideshow_control_delete.click(function(e){
		$slideshow_select_modal_title.text("請選擇要刪除的輪播圖");
		$slide_select_confirm.text("確認刪除");
		$slide_list.selectable("enable");
		$slide_select_confirm.on("click",deleteSlide);
		$slideshow_select_modal.modal("show")
		.one("hidden.bs.modal", function (e) {
			$slide_select_confirm.off("click",deleteSlide);
			$slide_list.selectable("disable").find("li").removeClass("slide_selected");			
		});
		
	});
	/* delete slideshow end */
  } );