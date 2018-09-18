jQuery(document).ready(function () {
    jQuery('.edit_personal').click(function(){
        if(jQuery('.popup-edit-personal').is(":visible")){
            jQuery('.popup-edit-personal').hide();
        }else{
            jQuery('.popup-edit-personal').show();
        }
    });
});