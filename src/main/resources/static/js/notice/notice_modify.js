function generateModify(index, type){
    let selection = generateNoticeType();

    let target = document.querySelector('#card'+index);
    let noticeType = target.children[2].children[1].innerText;
    let description = target.children[3].innerText;

    // 이미 선택된 값 선택하기
    let indexOf = selection.indexOf(noticeType);
    const insertAt = (str, sub, pos) => `${str.slice(0, pos)}${sub}${str.slice(pos)}`;
    selection = insertAt(selection, ' selected="selected"', indexOf-1);

    let inner = document.querySelector('#popup-area');
    inner.innerHTML =
        '<div class="wrap-notice-modify">\n' +
        '       <div class="background-notice-modify-buttons"></div>\n' +
        '       <div class="wrap-notice-modify-buttons">\n' +
        '               <div class="notice-modify-buttons">\n' +
        '\n' +
        '                   <div class="notice-modify-items notice-buttons-modify">\n' +
        '                       <textarea id="modify-description" >'+description+'</textarea>\n' +
        '                   </div>\n' +
                            selection +
        '                   <div>\n'+
        '                       <input class="notice-buttons" id="modify-button" type="submit" value="전송" onclick="modifySubmit('+index+',\''+type+'\')">\n' +
        '                       <input class="notice-buttons" id="modify-button-cancle" type="submit" value="취소" onclick="destroy_modify()">\n' +
        '                   </div>\n' +
        '               </div>\n' +
        '       </div>\n' +
        '</div>'
}

function modifySubmit(index, type){
    let description = document.querySelector('#modify-description').value;
    let notice_type = document.querySelector('#notice-category').value;
    console.log(notice_type)
    $.ajax({
        url: "/notice/modify-api",
        data: {'index':index, 'type':type, 'description': description, 'noticeType': notice_type},
        type: "POST",
        success : function(data){
            if(data === true){
                let target = document.querySelector('#card'+index);
                target.children[3].innerText = description;
                target.children[2].children[1].innerHTML =
                    '<span class="card-item-noticeType noticeType_'+notice_type+'">' +
                        noticeType_label[noticeType_name.indexOf(notice_type)] +
                    '</span>';

                console.log(noticeType_label[noticeType_name.indexOf(notice_type)])

                alert("수정되었습니다");
                document.querySelector('.wrap-notice-modify').remove();
            }
        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    }).always(()=>destroy_setting());

}

// 전송 클릭 시 ajax로 통신해서 수정하고
// 해당 html에서 description을 그냥 수정 후 창 닫기