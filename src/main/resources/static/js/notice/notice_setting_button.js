function showSettings(e, type){
    let index = e.getAttribute('id').substr(7);
    //let target = document.querySelector('#setting-div'+index);

    generateSetting(e, index, type)
}

function generateSetting(e, index, type){
    let inner = document.querySelector('#popup-area');
    inner.innerHTML =
        '<div class="wrap-notice-setting">'+
        '   <div class="background-notice-setting-buttons"></div>\n' +
        '   <div class="wrap-notice-setting-buttons"  onclick="destroy_setting()">\n' +
        '       <div class="notice-setting-buttons">\n' +
        '            <div class="notice-setting-items notice-buttons-modify" onclick="ajax_modify('+index+',\''+type+'\')">\n' +
        '                <span>\n' +
        '                    수정\n' +
        '                </span>\n' +
        '            </div>\n' +
        '            <div class="notice-setting-items notice-buttons-delete" onclick="ajax_delete('+index+',\''+type+'\')" >\n' +
        '               <span>\n' +
        '                    삭제\n' +
        '                </span>\n' +
        '          </div>\n' +
        '      </div>\n' +
        '   </div>' +
        '</div>'

}

function ajax_modify(index, type){
    generateModify(index, type);

}

function ajax_delete(index, type){

    $.ajax({
        url: "/notice/delete-api",
        data: {'index':index, 'type':type},
        type: "POST",
        success : function(data){
            if (data === true){
                let target = document.querySelector('#card'+index);
                target.innerHTML = '';
                target.classList.add('card-deleted');

                setTimeout(()=>{
                    target.remove();
                }, 1100)
            }
        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    }).always(()=>destroy_setting());
}

function destroy_setting(){

    document.querySelector('.wrap-notice-setting').remove();
}
function destroy_modify(){

    if (confirm("정말 취소하시겠습니까?")) {
        document.querySelector('.wrap-notice-modify').remove();
    }
    else {
        return null;
    }

}